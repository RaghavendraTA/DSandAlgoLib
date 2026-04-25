package org.buildwithraghu.javafeatures;

abstract class AbstractClass {
    protected int i;

    AbstractClass(int i) {
        this.i = i * 2;
    }

    public abstract int getVal();
}

class AbstractInherit extends AbstractClass {
    AbstractInherit(int i) {
        super(i);
    }

    @Override
    public int getVal() {
        return i;
    }
}

final class FinalClass {
    private final int i;
    FinalClass(int i) {
        this.i = i;
    }
    public int getVal() {
        return i;
    }
}

public class AbstractExample {
    public static void main(String[] args) {
        AbstractClass ac = new AbstractInherit(10);
        System.out.println(ac.i);
        FinalClass fc = new FinalClass(25);
        System.out.println(fc.getVal());
    }
}
