package org.buildwithraghu.lowleveldesign.inmemtaskscheduler;

public class ScheduledTask implements Comparable<ScheduledTask> {
    private final Runnable task;
    private long nextRunTime;
    private final long interval;

    public ScheduledTask(Runnable task, long delay, long interval) {
        this.task = task;
        this.nextRunTime = System.currentTimeMillis() + delay;
        this.interval = interval;
    }

    public long getNextRunTime() {
        return this.nextRunTime;
    }

    public Runnable getTask() {
        return this.task;
    }

    public long getInterval() {
        return this.interval;
    }

    public void setNextRunTime(Long nextRunTime) {
        this.nextRunTime = nextRunTime;
    }

    @Override
    public int compareTo(ScheduledTask other) {
        return Long.compare(this.nextRunTime, other.nextRunTime);
    }
}
