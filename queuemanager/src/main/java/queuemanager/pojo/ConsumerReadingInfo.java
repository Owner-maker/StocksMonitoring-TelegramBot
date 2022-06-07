package queuemanager.pojo;

public class ConsumerReadingInfo {
    private String brokerHost;
    private int brokerPort;
    private String topicName;
    private int partitionIndex;

    public ConsumerReadingInfo() {
    }

    public ConsumerReadingInfo(String brokerHost, int brokerPort, String topicName, int partitionIndex) {
        this.brokerHost = brokerHost;
        this.brokerPort = brokerPort;
        this.topicName = topicName;
        this.partitionIndex = partitionIndex;
    }

    public String getBrokerHost() {
        return brokerHost;
    }

    public void setBrokerHost(String brokerHost) {
        this.brokerHost = brokerHost;
    }

    public int getBrokerPort() {
        return brokerPort;
    }

    public void setBrokerPort(int brokerPort) {
        this.brokerPort = brokerPort;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getPartitionIndex() {
        return partitionIndex;
    }

    public void setPartitionIndex(int partitionIndex) {
        this.partitionIndex = partitionIndex;
    }
}
