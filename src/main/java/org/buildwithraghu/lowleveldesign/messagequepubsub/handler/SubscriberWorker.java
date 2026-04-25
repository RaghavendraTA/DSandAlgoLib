package org.buildwithraghu.lowleveldesign.messagequepubsub.handler;

import org.buildwithraghu.lowleveldesign.messagequepubsub.model.Message;
import org.buildwithraghu.lowleveldesign.messagequepubsub.model.Topic;
import org.buildwithraghu.lowleveldesign.messagequepubsub.model.TopicSubscriber;

public class SubscriberWorker implements Runnable {
    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public SubscriberWorker(final Topic topic, final TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @Override
    public void run() {
        synchronized (topicSubscriber) {
            do {
                int curOffset = topicSubscriber.getOffset().get();
                while (curOffset >= topic.getMessages().size()) {
                    try {
                        topicSubscriber.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                Message message = topic.getMessages().get(curOffset);
                try {
                    topicSubscriber.getSubscriber().consume(message);
                } catch(Exception e) {
                    // TODO: fix the exception caught
                }

                topicSubscriber.getOffset().compareAndSet(curOffset, curOffset + 1);
            } while (true);
        }
    }

    public void wakeUpIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}
