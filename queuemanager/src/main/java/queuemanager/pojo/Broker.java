package queuemanager.pojo;

import java.util.HashMap;
import java.util.Map;

public class Broker {
    private String addressURL;
    private Map<String,Topic> topics;
    private boolean isCoordinator;

    public Broker(){
    }

    public Broker(String addressURL, Map<String, Topic> topics, boolean isCoordinator) {
        this.addressURL = addressURL;
        this.topics = new HashMap<>(topics);
        this.isCoordinator = isCoordinator;
    }

    public String getAddressURL() {
        return addressURL;
    }

    public Map<String, Topic> getTopics() {
        return new HashMap<>(topics);
    }

    public boolean isCoordinator() {
        return isCoordinator;
    }
}
