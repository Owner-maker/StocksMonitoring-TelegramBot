package queuemanager.pojo;

import java.util.Map;

public class Broker {
    private String host;
    private int port;
    private Map<String, Topic> topics;

    public Broker() {
    }

    public Broker(String host, int port) {
        this(host, port, null);
    }

    public Broker(String host, int port, Map<String, Topic> topics) {
        this.host = host;
        this.port = port;
        this.topics = topics;
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

    public Map<String, Topic> getTopics() {
        return topics;
    }

    public void setTopics(Map<String, Topic> topics) {
        this.topics = topics;
    }
}
