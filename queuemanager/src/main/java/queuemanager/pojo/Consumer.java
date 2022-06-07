package queuemanager.pojo;

public class Consumer {
    private String host;
    private int port;
    private String groupId;
    private ConsumerReadingInfo readingInfo;

    public Consumer() {
    }

    public Consumer(String host, int port, String groupId) {
        this.host = host;
        this.port = port;
        this.groupId = groupId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public ConsumerReadingInfo getReadingInfo() {
        return readingInfo;
    }

    public void setReadingInfo(ConsumerReadingInfo readingInfo) {
        this.readingInfo = readingInfo;
    }
}
