package org.buildwithraghu.javafeatures.threadsandprocesses;

class Thread1 extends Thread {
    @Override
    public void run() {
        try {
            for(int i = 0; i < 50; i++) {
                Thread.sleep(100);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class MultiThreading {
    public static void main(String[] args) throws InterruptedException {
        new Thread1().start();
        Thread.sleep(150);
        System.out.println("This will print before the Thread1");
    }
}
