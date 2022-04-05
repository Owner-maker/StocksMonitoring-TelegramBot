package queuenode0.pojo;

import java.util.ArrayList;

public class Broker {
    private String address;
    private ArrayList<Topic> topics;

    public Broker(String address, ArrayList<Topic> topics) {
        this.address = address;
        this.topics = topics;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }
}
