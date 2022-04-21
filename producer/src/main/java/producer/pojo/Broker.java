package producer.pojo;

import java.util.HashMap;
import java.util.Map;

public class Broker {
    private String addressURL;
    private Map<String,Topic> topics;

    public Broker(){
    }

    public Broker(String addressURL, Map<String, Topic> topics) {
        this.addressURL = addressURL;
        this.topics = new HashMap<>(topics);
    }

    public String getAddressURL() {
        return addressURL;
    }

    public Map<String, Topic> getTopics() {
        return new HashMap<>(topics);
    }

    public void setAddressURL(String addressURL) {
        this.addressURL = addressURL;
    }

    public void setTopics(Map<String, Topic> topics) {
        this.topics = topics;
    }
}
