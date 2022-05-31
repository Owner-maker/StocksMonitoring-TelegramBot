package queuemanager.pojo;

public class Consumer {
    private String host;
    private int port;
    private int groupId;

    public Consumer() {
    }

    public Consumer(String host, int port, int groupId) {
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
