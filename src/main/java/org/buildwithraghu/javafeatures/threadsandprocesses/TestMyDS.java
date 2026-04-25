package org.buildwithraghu.javafeatures.threadsandprocesses;

import org.buildwithraghu.javafeatures.concurrentDS.MyDataStructure;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMyDS {

    public static void main(String[] args) {
        MyDataStructure mds = new MyDataStructure();
        Runnable producer = () -> {
            for(int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(50);
                    mds.add((i + 1) * 10);
                    System.out.println("(" + i + ") Added = " + ((i + 1) * 10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        ExecutorService exs = Executors.newCachedThreadPool();
        exs.submit(producer);
        exs.submit(() -> {
            for(int i = 0; i < 20; i++) {
                try {
                    System.out.println("(" + i + ") Got = " + mds.take());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        exs.shutdown();
    }
}
