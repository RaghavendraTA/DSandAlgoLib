package org.buildwithraghu.revision;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FizzBuzz {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int counter = 1;
    private final Integer n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    private boolean fizz() {
        lock.lock();
        try {
            while (counter <= n && !(counter % 3 == 0 && counter % 5 != 0)) {
                condition.await();
            }
            if (counter > n) {
                condition.signalAll();
                return false;
            }
            System.out.println("Fizz");
            counter++;
            condition.signalAll();
            return true;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            lock.unlock();
        }
    }

    private boolean buzz() {
        lock.lock();
        try {
            while (counter <= n && !(counter % 5 == 0 && counter % 3 != 0)) {
                condition.await();
            }
            if (counter > n) {
                condition.signalAll();
                return false;
            }
            System.out.println("Buzz");
            counter++;
            condition.signalAll();
            return true;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            lock.unlock();
        }
    }

    private boolean fizzBuzz() {
        lock.lock();
        try {
            while (counter <= n && !(counter % 3 == 0 && counter % 5 == 0)) {
                condition.await();
            }
            if (counter > n) {
                condition.signalAll();
                return false;
            }
            System.out.println("FizzBuzz");
            counter++;
            condition.signalAll();
            return true;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            lock.unlock();
        }
    }

    private boolean normal() {
        lock.lock();
        try {
            while (counter <= n && (counter % 3 == 0 || counter % 5 == 0)) {
                condition.await();
            }
            if (counter > n) {
                condition.signalAll();
                return false;
            }
            System.out.println(counter);
            counter++;
            condition.signalAll();
            return true;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            lock.unlock();
        }
    }

    static void main() throws Exception {
        int n = 50;
        FizzBuzz fizzBuzz = new FizzBuzz(n);

        Thread fizzThread = new Thread(() -> { while(fizzBuzz.fizz()); });
        Thread buzzThread = new Thread(() -> { while(fizzBuzz.buzz()); });
        Thread fizzBuzzThread = new Thread(() -> { while(fizzBuzz.fizzBuzz()); });
        Thread normalThread = new Thread(() -> { while(fizzBuzz.normal()); });

        fizzThread.start();
        buzzThread.start();
        fizzBuzzThread.start();
        normalThread.start();

        fizzThread.join();
        buzzThread.join();
        fizzBuzzThread.join();
        normalThread.join();

        System.out.println("Main completed");
    }
}
