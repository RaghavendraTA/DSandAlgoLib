package org.buildwithraghu.lowleveldesign.kafkalike;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class Topic {

    private final String name;
    private final List<Message> messages = new ArrayList<>(); // guarded by 'this' for append+read snapshot
    private final AtomicLong nextOffset = new AtomicLong(0);

    // subscribers registry: subscriberId -> SubscriberContext
    private final ConcurrentMap<String, SubscriberContext> subscribers = new ConcurrentHashMap<>();

    Topic(String name) { this.name = name; }

    // append message and get its offset
    public Message append(String payload) {
        long offset = nextOffset.getAndIncrement();
        Message m = new Message(offset, payload);
        synchronized (this) {
            messages.add(m);
        }
        // notify subscribers asynchronously via each subscriber's executor
        for (SubscriberContext sc : subscribers.values()) {
            sc.enqueueNotification();
        }
        return m;
    }

    // read messages from offset (exclusive semantics: nextOffset -> returns messages with offset >= fromOffset)
    public List<Message> readFrom(long fromOffset, int maxMessages) {
        synchronized (this) {
            long currentSize = messages.size();
            if (fromOffset >= currentSize) {
                return Collections.emptyList();
            }
            int startIndex = (int) fromOffset;
            int endIndex = (int) Math.min(currentSize, startIndex + maxMessages);
            return new ArrayList<>(messages.subList(startIndex, endIndex));
        }
    }

    public void addSubscriber(String subscriberId, SubscriberContext ctx) {
        subscribers.put(subscriberId, ctx);
    }

    public void removeSubscriber(String subscriberId) {
        subscribers.remove(subscriberId);
    }

    public long getLatestOffset() {
        return nextOffset.get() - 1;
    }

    public ConcurrentMap<String, SubscriberContext> getSubscribers() {
        return subscribers;
    }
}
