package queue_broker_0.pojo;

import java.sql.Timestamp;

public class Message {
    private String key;
    private String value;
    private Timestamp timestamp;
    private String topicName;
    private int partitionNumber;

    public Message(String key, String value, Timestamp timestamp, String topicName, int partitionNumber) {
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
        this.topicName = topicName;
        this.partitionNumber = partitionNumber;
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

    public int getPartitionNumber() {
        return partitionNumber;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setPartitionNumber(int partitionNumber) {
        this.partitionNumber = partitionNumber;
    }

    @Override
    public String toString() {
        return String.format("key: %s timestamp: %s value: %s\n",key,timestamp,value);
    }
}
