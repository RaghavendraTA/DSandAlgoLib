package org.buildwithraghu.concurrency;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueue {

    private Deque<Integer> deque;
    private int capacity;

    ReentrantLock lock = new ReentrantLock();
    Condition full = lock.newCondition();
    Condition empty = lock.newCondition();

    public BoundedBlockingQueue(int capacity) {
        deque = new ArrayDeque<>();
        this.capacity = capacity;
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            while (deque.size() == capacity)
                full.await();
            deque.addLast(element);
            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        try {
            while(deque.isEmpty())
                empty.await();
            int rf = deque.removeFirst();
            full.signal();
            return rf;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return deque.size();
        } finally {
            lock.unlock();
        }
    }

    static void main() {
        BoundedBlockingQueue queue = new BoundedBlockingQueue(10);
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(() -> {
            // Producer
            try {
                for(int i = 0; i < 10; i++) {
                    Thread.sleep(50);
                    System.out.println("Enqueue = " + i);
                    queue.enqueue(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        service.submit(() -> {
            // Consumer
            try {
                for(int i = 0; i < 10; i++) {
                    System.out.println("Dequeue = " + queue.dequeue());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        service.shutdown();
    }
}
