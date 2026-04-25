package org.buildwithraghu.lowleveldesign.kafkalike;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class SimpleBroker {

    // Broker holds topics
    private final ConcurrentMap<String, Topic> topics = new ConcurrentHashMap<>();

    // Create or get topic
    public Topic getTopic(String name, boolean createIfMissing) {
        Topic t = topics.get(name);
        if (t == null && createIfMissing) {
            Topic newT = new Topic(name);
            Topic existing = topics.putIfAbsent(name, newT);
            t = existing == null ? newT : existing;
        }
        return t;
    }

    // API: publish
    public Message publish(String topicName, String payload) {
        Topic topic = getTopic(topicName, true);
        return topic.append(payload);
    }

    // API: subscribe - returns subscriberId
    public String subscribe(String topicName, Consumer<Message> callback) {
        return subscribe(topicName, 0L, callback, 10);
    }

    // subscribe with start offset and batch size
    public String subscribe(String topicName, long startOffset, Consumer<Message> callback, int batchSize) {
        Topic topic = getTopic(topicName, true);
        String subscriberId = UUID.randomUUID().toString();
        SubscriberContext ctx = new SubscriberContext(subscriberId, topicName, startOffset, callback, this, batchSize);
        topic.addSubscriber(subscriberId, ctx);
        // On subscribe, notify so the subscriber drains existing messages (if any)
        ctx.enqueueNotification();
        return subscriberId;
    }

    // unsubscribe
    public void unsubscribe(String topicName, String subscriberId) {
        Topic t = getTopic(topicName, false);
        if (t != null) {
            SubscriberContext ctx = t.getSubscribers().remove(subscriberId);
            if (ctx != null) ctx.shutdown();
        }
    }

    public static void main(String[] args) throws Exception {
        SimpleBroker broker = new SimpleBroker();

        // Subscriber A starts from offset 0
        String subA = broker.subscribe("orders", msg -> {
            System.out.println("Subscriber-A got: " + msg);
        });

        // Subscriber B starts from offset 0 (independent)
        String subB = broker.subscribe("orders", msg -> {
            System.out.println("Subscriber-B got: " + msg);
        });

        // Multiple producers publishing concurrently
        ExecutorService producers = Executors.newFixedThreadPool(3);
        for (int p = 0; p < 3; p++) {
            final int pid = p;
            producers.submit(() -> {
                for (int i = 0; i < 5; i++) {
                    String payload = "producer-" + pid + "-item-" + i;
                    Message m = broker.publish("orders", payload);
                    System.out.println("Published: " + m + " by producer " + pid);
                    try { Thread.sleep(ThreadLocalRandom.current().nextInt(50, 150)); } catch (InterruptedException ignored) {}
                }
            });
        }

        producers.shutdown();
        producers.awaitTermination(5, TimeUnit.SECONDS);

        // give subscribers time to process
        Thread.sleep(1000);

        // unsubscribe B and publish more messages
        broker.unsubscribe("orders", subB);
        System.out.println("Subscriber-B unsubscribed");

        broker.publish("orders", "final-item-1");
        broker.publish("orders", "final-item-2");

        Thread.sleep(500); // allow A to receive
        System.out.println("Demo finished.");
        System.exit(0);
    }
}
