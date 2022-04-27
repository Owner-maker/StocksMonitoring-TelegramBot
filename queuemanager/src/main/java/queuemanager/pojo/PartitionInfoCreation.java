package queuemanager.pojo;

public class PartitionInfoCreation {
    private String topicName;
    private int partitionQuantity;
    private int amountOfExistPartitions;

    public PartitionInfoCreation() {
    }

    public PartitionInfoCreation(String topicName, int partitionQuantity, int amountOfExistPartitions) {
        this.topicName = topicName;
        this.partitionQuantity = partitionQuantity;
        this.amountOfExistPartitions = amountOfExistPartitions;
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

    public int getAmountOfExistPartitions() {
        return amountOfExistPartitions;
    }

    public void setAmountOfExistPartitions(int amountOfExistPartitions) {
        this.amountOfExistPartitions = amountOfExistPartitions;
    }
}
