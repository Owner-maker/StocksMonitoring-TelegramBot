package consumer.pojo.message;

import java.sql.Timestamp;

public class Message {
    private String key;
    private Timestamp timestamp;
    private String value;

    public Message() {
    }

    public Message(String key, Timestamp timestamp, String value) {
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("key: %s timestamp: %s value: %s%n", key, timestamp, value);
    }
}
