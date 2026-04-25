package org.buildwithraghu.javafeatures.concurrentDS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class AtomicValuesEx {

    public final static AtomicLong atomicLong = new AtomicLong();

    public final static LongAdder longAdder = new LongAdder();

    public static void jobRunnerWithAtomicLong() {
        Runnable producer = () -> {
            for(int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(200);
                    System.out.println("(" + i + ") Atomic INCR = " + atomicLong.incrementAndGet());
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
                    Thread.sleep(250);
                    System.out.println("(" + i + ") Atomic DCRE = " + atomicLong.decrementAndGet());
                } catch(InterruptedException e) {
                    throw new RuntimeException();
                }
            }
        });
        exs.shutdown();
    }

    public static void jobRunnerWithLongAdder() {
        Runnable producer = () -> {
            for(int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(200);
                    longAdder.increment();
                    System.out.println("(" + i + ") Adder INCR = " + longAdder.longValue());
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
                    Thread.sleep(250);
                    longAdder.decrement();
                    System.out.println("(" + i + ") Adder DCRE = " + longAdder.longValue());
                } catch(InterruptedException e) {
                    throw new RuntimeException();
                }
            }
        });
        exs.shutdown();
    }

    public static void main(String[] args) {
        jobRunnerWithAtomicLong();
        //jobRunnerWithLongAdder();
    }
}
