package org.buildwithraghu.revision;

import org.buildwithraghu.lowleveldesign.inmemtaskscheduler.ScheduledTask;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TaskSchedulerRevision {

    private final PriorityQueue<ScheduledTask> queue = new PriorityQueue<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition newTask = lock.newCondition();
    private final ExecutorService workerService;

    private volatile boolean running = true;

    public TaskSchedulerRevision(int threads) {
        this.workerService = Executors.newFixedThreadPool(threads);
        Thread dispatcher = new Thread(this::dispatchLoop);
        dispatcher.setDaemon(true);
        dispatcher.start();
    }

    public void schedule(Runnable task, long delayMillis) {
        scheduleInternal(task, delayMillis, 0);
    }

    public void scheduleFixedInterval(Runnable task, long repeat) {
        scheduleInternal(task, repeat, repeat);
    }

    public void shutdown() {
        running = false;
        lock.lock();
        try {
            newTask.signalAll();
        } finally {
            lock.unlock();
        }
        workerService.shutdown();
    }

    // private methods
    private void scheduleInternal(Runnable task, long delayMillis, long repeat) {
        lock.lock();
        try {
            queue.offer(new ScheduledTask(task, delayMillis, repeat));
            newTask.signal();
        } finally {
            lock.unlock();
        }
    }

    private void dispatchLoop() {
        while(running) {
            lock.lock();
            try {
                while(queue.isEmpty()) {
                    newTask.await();
                }

                long currentTime = System.currentTimeMillis();
                ScheduledTask next = queue.peek();
                long waitTime = next.getNextRunTime() - currentTime;

                if (waitTime > 0) {
                    newTask.await(waitTime, TimeUnit.MILLISECONDS);
                } else {
                    queue.poll();
                    workerService.submit(next.getTask());

                    if (next.getInterval() > 0) {
                        next.setNextRunTime(currentTime + next.getInterval());
                        queue.offer(next);
                        newTask.signal();
                    }
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    static void main() throws InterruptedException {
        TaskSchedulerRevision taskScheduler = new TaskSchedulerRevision(3);

        taskScheduler.schedule(() -> System.out.println("Executed Task A at: " + System.currentTimeMillis()), 2000);
        taskScheduler.scheduleFixedInterval(() -> System.out.println("Repeating Task B at: " + System.currentTimeMillis()), 1000);

        Thread.sleep(6000);
        taskScheduler.shutdown();
        System.out.println("Scheduler is stopped");
    }
}
