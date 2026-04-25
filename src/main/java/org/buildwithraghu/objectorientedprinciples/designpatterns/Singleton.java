package org.buildwithraghu.objectorientedprinciples.designpatterns;

class SomeSingleton {

    public static volatile SomeSingleton singleton;

    private SomeSingleton() {}

    // If I push synchronized to the method signature every call will look for thread safety
    // which will slower the performance
    // its only synchronized when the object is needed.
    public static SomeSingleton getInstance() {
        if (singleton == null) {
            synchronized (SomeSingleton.class) {
                singleton = new SomeSingleton();
            }
        }
        return singleton;
    }

    public void someMethod() {
        // Code here will be used across repository
        // values and state will remain for all usage
    }

    private void doSomething() {}
}

// Syntactically Better, thread Safe, Lazy initialization
class BetterSingleton {

    private BetterSingleton() {}

    // even the Holder class will not be loaded until getInstance() called
    // JVM handles static init in thread safe manner
    private static class Holder {
        private static final BetterSingleton INSTANCE = new BetterSingleton();
    }

    public static BetterSingleton getInstance() {
        return Holder.INSTANCE;
    }

    public void someMethod() {
        // Shared logic
    }

    private void doSomething() {}
}

public class Singleton {
    public static void main(String[] args) {
        BetterSingleton.getInstance().someMethod();
    }
}