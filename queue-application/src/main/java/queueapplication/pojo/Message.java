package queueapplication.pojo;

import java.sql.Timestamp;

public class Message {
    private String key;
    private String value;
    private Timestamp timestamp;
    private String topicName;

    public Message(String key, String value,String topicName) {
        this.key = key;
        this.value = value;
        this.timestamp = new Timestamp(System.nanoTime());
        this.topicName = topicName;
    }

    public Message(Message message){
        this(message.getKey(), message.getValue(), message.getTopicName());
        this.timestamp = message.getTimestamp();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getTopicName() {
        return topicName;
    }
}
