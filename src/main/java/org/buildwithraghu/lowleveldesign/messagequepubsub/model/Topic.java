package org.buildwithraghu.lowleveldesign.messagequepubsub.model;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private final String topicName;
    private final String topicId;
    private final List<Message> messages;
    private final List<TopicSubscriber> subscribers;

    public Topic(final String topicName, final String topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public synchronized void addMessage(final Message message) {
        messages.add(message);
    }

    public void addSubscriber(final TopicSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTopicId() {
        return topicId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<TopicSubscriber> getSubscribers() {
        return subscribers;
    }
}
