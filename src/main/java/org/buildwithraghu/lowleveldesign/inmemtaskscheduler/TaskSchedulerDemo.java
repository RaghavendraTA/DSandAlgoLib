package org.buildwithraghu.lowleveldesign.inmemtaskscheduler;

public class TaskSchedulerDemo {

    public static void main(String[] args) throws InterruptedException {
        TaskScheduler scheduler = new TaskScheduler(3);

        scheduler.schedule(() -> System.out.println("Task A executed at " + System.currentTimeMillis()), 2000);
        scheduler.scheduleAtFixedInterval(() -> System.out.println("Repeating task B at " + System.currentTimeMillis()), 1000);

        Thread.sleep(6000);
        scheduler.shutdown();
        System.out.println("Scheduler stopped.");
    }
}
