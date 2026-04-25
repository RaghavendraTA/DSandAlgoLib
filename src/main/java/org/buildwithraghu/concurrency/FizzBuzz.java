package org.buildwithraghu.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class FizzBuzz {

    private int n;
    private int current = 1;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        while(true) {
            lock.lock();
            try {
                while (current <= n && !(current % 3 == 0 && current % 5 != 0)) {
                    condition.await();
                }
                if (current > n)
                    return;
                printFizz.run();
                current++;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public void buzz(Runnable printbuzz) throws InterruptedException {
        while(true) {
            lock.lock();
            try {
                while (current <= n && !(current % 3 != 0 && current % 5 == 0)) {
                    condition.await();
                }
                if (current > n)
                    return;
                printbuzz.run();
                current++;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public void fizzbuzz(Runnable printfizBuzz) throws InterruptedException {
        while(true) {
            lock.lock();
            try {
                while (current <= n && !(current % 3 == 0 && current % 5 == 0)) {
                    condition.await();
                }
                if (current > n)
                    return;
                printfizBuzz.run();
                current++;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            lock.lock();
            try {
                while (current <= n && (current % 3 == 0 || current % 5 == 0)) {
                    condition.await();
                }

                if (current > n) return;

                printNumber.accept(current);
                current++;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

        FizzBuzz fizzBuzz = new FizzBuzz(15);

        new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.println("fizz"));
            } catch (Exception e) {}
        }).start();

        new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.println("buzz"));
            } catch (Exception e) {}
        }).start();

        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.println("fizzbuzz"));
            } catch (Exception e) {}
        }).start();

        new Thread(() -> {
            try {
                fizzBuzz.number(x -> System.out.println(x));
            } catch (Exception e) {}
        }).start();
    }
}