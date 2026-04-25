package org.buildwithraghu.lowleveldesign.kafkalike;

public class Message {
    private final long offset;
    private final String payload;
    private final long timestamp;

    public Message(long offset, String payload) {
        this.offset = offset;
        this.payload = payload;
        this.timestamp = System.currentTimeMillis();
    }

    public long getOffset() { return offset; }

    public String getPayload() {
        return payload;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Message[offset=" + offset + ", payload=" + payload + "]";
    }
}
