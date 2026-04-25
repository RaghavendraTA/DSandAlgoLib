package org.buildwithraghu.concurrency;

class SampleThread extends Thread {
    int id, sleep;
    SampleThread(int id, int sleeper) {
        this.id = id;
        this.sleep = sleeper;
    }
    @Override
    public void run() {
        try {
            for(int i = 0; i < 100; i++) {
                Thread.sleep(this.sleep);
                System.out.println("Thread(" + id + ") = " + i);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

public class SimpleThread {
    static void main() throws Exception {
        SampleThread thread1 = new SampleThread(1, 200);
        SampleThread thread2 = new SampleThread(2, 100);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
