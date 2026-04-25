package org.buildwithraghu.javafeatures.threadsandprocesses;

import java.util.concurrent.*;

public class ManagedConcurrency {

    public void taskThatRunWithinCallerThread() {
        Executor sameThread = Runnable::run; // runs task in the caller thread
        sameThread.execute(() -> System.out.println(Thread.currentThread().getName()));
    }

    public void taskThatAlwaysCreatesNewThread() {
        Executor newThreadExecutor = r -> new Thread(r).start();
        newThreadExecutor.execute(() -> System.out.println(Thread.currentThread().getName()));
    }

    public void testRunnableTask() {
        // Cannot capture output from Runnable in that case use callable
        Runnable task = () -> {
            System.out.println("Inside the task");
        };
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(task);
    }

    public void testCallableTask() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            return 2;
        };
        int processes = Runtime.getRuntime().availableProcessors();
        try (ExecutorService exs = Executors.newFixedThreadPool(processes)) {
            Future<Integer> result = exs.submit(callable);
            System.out.println(result.get());
            exs.shutdown();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Inside the main");
        ManagedConcurrency mc = new ManagedConcurrency();
        mc.testRunnableTask();
        // mc.testCallableTask();
        // mc.taskThatRunWithinCallerThread();
        // mc.taskThatAlwaysCreatesNewThread();
    }
}
