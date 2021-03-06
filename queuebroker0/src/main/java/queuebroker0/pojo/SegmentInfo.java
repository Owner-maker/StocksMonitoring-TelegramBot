package queuebroker0.pojo;

public class SegmentInfo {
    private String topicName;
    private int partitionNumber;

    public SegmentInfo() {
    }

    public SegmentInfo(String topicName, int partitionNumber) {
        this.topicName = topicName;
        this.partitionNumber = partitionNumber;
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
}
