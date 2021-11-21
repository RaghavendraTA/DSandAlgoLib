package multithreading.executorexamples;

/*
 * created by raghavendra.ta on 09-Jul-2021
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadObject extends Thread {

    private final long threadId;

    ThreadObject() {
        this.threadId = currentThread().getId();
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Running thread " + threadId);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SampleExecutors {

    public ExecutorService executorService = Executors.newScheduledThreadPool(10);

    public void spawn() {
        executorService.submit(ThreadObject::new);
        executorService.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        new SampleExecutors().spawn();
        Thread.sleep(5000);
    }

}
