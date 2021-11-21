package multithreading.executorexamples;

/*
 * created by raghavendra.ta on 09-Jul-2021
 */

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class TasksExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Consumer<Integer[][]> print2DArray = matrix -> {
            for(Integer[] arr: matrix)
                System.out.println(Arrays.toString(arr));
        };

        print2DArray.accept(new Integer[][]{{1, 2}, {3, 4}});

        Runnable runnableTask = () -> {
            System.out.println("Testing");
        };

        Callable<String> callableTask = () -> {
            return "Checking1";
        };

        Callable<String> callableTask2 = () -> {
            return "Checking2";
        };

        ExecutorService service = Executors.newScheduledThreadPool(10);

        //Future<String> result = service.submit(callableTask);

        List<Future<String>> results = service.invokeAll(Arrays.asList(callableTask, callableTask));

        service.shutdown();

        Thread.sleep(1000);

        for(Future<String> result: results)
            System.out.println(result.get());
    }
}
