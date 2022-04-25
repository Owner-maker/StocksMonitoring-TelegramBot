package queuebroker0.pojo.partition;

public class PartitionInfoCreation {
    private String topicName;
    private int partitionQuantity;

    public PartitionInfoCreation(String topicName, int partitionQuantity) {
        this.topicName = topicName;
        this.partitionQuantity = partitionQuantity;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getPartitionQuantity() {
        return partitionQuantity;
    }

    public void setPartitionQuantity(int partitionQuantity) {
        this.partitionQuantity = partitionQuantity;
    }
}
