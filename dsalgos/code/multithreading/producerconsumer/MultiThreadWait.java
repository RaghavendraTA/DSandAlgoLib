package multithreading.producerconsumer;

/*
 * created by raghavendra.ta on 03-Jul-2021
 */

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

class SampleThread extends Thread {

    private final String threadName;
    private final SynchronousQueue<Integer> queue;

    SampleThread(String threadName, SynchronousQueue<Integer> queue) {

        this.threadName = threadName;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread = " + threadName + ", i = " + queue.take());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class MultiThreadWait {

    public static void main(String[] args) {

        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        try {
            for (int i = 1; i <= 9; i++)
                queue.put(i + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<SampleThread> threadList = Arrays.asList(
                new SampleThread("1", queue),
                new SampleThread("2", queue),
                new SampleThread("3", queue)
        );

        for(int i = 0; i < 9; i++) {
            try {
                SampleThread t = threadList.get(i % 3);
                t.start();
                //t.join();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}