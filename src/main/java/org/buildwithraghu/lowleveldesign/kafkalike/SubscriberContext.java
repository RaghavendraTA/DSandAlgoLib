package org.buildwithraghu.lowleveldesign.kafkalike;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class SubscriberContext {

    private final String id;
    private final String topicName;
    private final Consumer<Message> callback;
    private final ExecutorService executor; // single-threaded to preserve per-subscriber ordering
    private final SimpleBroker broker;
    private final AtomicLong nextOffset; // offset of next message to consume
    private final int batchSize;

    // A lightweight notification gate to avoid scheduling too many tasks
    // If a notification is already enqueued, further notifications don't enqueue new tasks.
    private final AtomicBoolean notified = new AtomicBoolean(false);

    SubscriberContext(String id, String topicName, long startOffset,
                      Consumer<Message> callback, SimpleBroker broker, int batchSize) {
        this.id = id;
        this.topicName = topicName;
        this.callback = callback;
        this.executor = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setName("subscriber-" + id);
            t.setDaemon(true);
            return t;
        });
        this.nextOffset = new AtomicLong(startOffset);
        this.broker = broker;
        this.batchSize = batchSize;
    }

    // Called by topic to enqueue that new messages exist
    public void enqueueNotification() {
        // Attempt to set notified from false -> true; only the first successful caller schedules a drain task
        if (notified.compareAndSet(false, true)) {
            executor.submit(this::drain);
        }
    }

    // Drain: read available messages and call the callback
    private void drain() {
        try {
            Topic topic = broker.getTopic(topicName, false);
            if (topic == null) return;

            while (true) {
                long offset = nextOffset.get();
                List<Message> batch = topic.readFrom(offset, batchSize);
                if (batch.isEmpty()) break;
                for (Message m : batch) {
                    try {
                        callback.accept(m);
                    } catch (Throwable t) {
                        // subscriber callback error: log & continue
                        System.err.println("Subscriber[" + id + "] callback error: " + t.getMessage());
                    }
                    nextOffset.incrementAndGet();
                }
            }
        } finally {
            // allow future notifications
            notified.set(false);
            // Edge case: if messages arrived between finishing and clearing notified, re-schedule
            Topic topic = broker.getTopic(topicName, false);
            if (topic != null) {
                long latest = topic.getLatestOffset();
                if (nextOffset.get() <= latest) {
                    // new messages exist, attempt to re-enqueue
                    if (notified.compareAndSet(false, true)) {
                        executor.submit(this::drain);
                    }
                }
            }
        }
    }

    public void shutdown() {
        executor.shutdownNow();
    }
}
