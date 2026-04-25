package org.buildwithraghu.designalgo;

import java.util.concurrent.atomic.AtomicLongArray;

public class Bank {

    AtomicLongArray amount;

    public Bank(long[] balance) {
        this.amount = new AtomicLongArray(balance);
    }

    public boolean transfer(int account1, int account2, long money) {
        if (account1 > this.amount.length() || account2 > this.amount.length() || this.amount.get(account1-1) < money)
            return false;
        this.amount.addAndGet(account1-1, -money);
        this.amount.addAndGet(account2-1, money);
        return true;
    }

    public boolean deposit(int account, long money) {
        if (account > this.amount.length())
            return false;
        this.amount.addAndGet(account-1, money);
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (account > this.amount.length() || this.amount.get(account-1) < money) {
            return false;
        }
        this.amount.addAndGet(account-1, -money);
        return true;
    }
}

// Can also try with lock -ReentrantLock