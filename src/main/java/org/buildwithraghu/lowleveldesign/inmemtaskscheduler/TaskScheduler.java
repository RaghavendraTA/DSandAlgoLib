package org.buildwithraghu.lowleveldesign.inmemtaskscheduler;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TaskScheduler {

    private final PriorityQueue<ScheduledTask> queue = new PriorityQueue<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition newTaskArrived = lock.newCondition();
    private final ExecutorService workers;
    private volatile boolean running = true;

    public TaskScheduler(int numThreads) {
        this.workers = Executors.newFixedThreadPool(numThreads);
        Thread dispatcher = new Thread(this::dispatchLoop);
        dispatcher.setDaemon(true);
        dispatcher.start();
    }

    public void schedule(Runnable task, long delayMillis) {
        scheduleInternal(task, delayMillis, 0);
    }

    public void scheduleAtFixedInterval(Runnable task, long intervalMillis) {
        scheduleInternal(task, intervalMillis, intervalMillis);
    }

    private void scheduleInternal(Runnable task, long delay, long interval) {
        lock.lock();
        try {
            queue.offer(new ScheduledTask(task, delay, interval));
            newTaskArrived.signal(); // wake dispatcher
        } finally {
            lock.unlock();
        }
    }

    private void dispatchLoop() {
        while (running) {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    newTaskArrived.await();
                }

                long currentTime = System.currentTimeMillis();
                ScheduledTask next = queue.peek();
                long waitTime = next.getNextRunTime() - currentTime;

                if (waitTime > 0) {
                    newTaskArrived.await(waitTime, TimeUnit.MILLISECONDS);
                } else {
                    queue.poll(); // remove head
                    workers.submit(next.getTask());

                    if (next.getInterval() > 0) {
                        next.setNextRunTime(currentTime + next.getInterval());
                        queue.offer(next); // reschedule
                        newTaskArrived.signal();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void shutdown() {
        running = false;
        lock.lock();
        try {
            newTaskArrived.signalAll();
        } finally {
            lock.unlock();
        }
        workers.shutdown();
    }
}