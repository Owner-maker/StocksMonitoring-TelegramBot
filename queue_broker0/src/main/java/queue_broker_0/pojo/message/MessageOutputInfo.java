package queue_broker_0.pojo.message;

public class MessageOutputInfo {
    private String topicName;
    private int partitionNumber;
    private int offset;

    public MessageOutputInfo() {
    }

    public MessageOutputInfo(String topicName, int partitionNumber, int offset) {
        this.topicName = topicName;
        this.partitionNumber = partitionNumber;
        this.offset = offset;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getPartitionNumber() {
        return partitionNumber;
    }

    public void setPartitionNumber(int partitionNumber) {
        this.partitionNumber = partitionNumber;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
