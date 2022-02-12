package multithreading.producerconsumer;

/*
 * created by raghavendra.ta on 03-Jul-2021
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

class Producer implements Runnable {

    private final LinkedBlockingQueue<Integer> queue;

    public Producer(LinkedBlockingQueue<Integer> queue) { this.queue = queue; }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Sent = " + i);
                queue.put(i);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {

    private final LinkedBlockingQueue<Integer> queue;

    public Consumer(LinkedBlockingQueue<Integer> queue) { this.queue = queue; }

    @Override
    public void run() {
        try {
            for(int i = 0; i < 10; i++)
                System.out.println("Consumed = " + queue.take());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class ExecutorExample {

    public static void main(String[] args) {

        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        ExecutorService executor = Executors.newScheduledThreadPool(10);

        executor.submit(new Producer(queue));
        executor.submit(new Consumer(queue));
        executor.shutdown();
    }

}
