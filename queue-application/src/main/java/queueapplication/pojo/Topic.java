package queueapplication.pojo;

import java.sql.Timestamp;

public class Topic {
    private String name;
    private Timestamp timestamp;

    public Topic(String name) {
        this.name = name;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("name:%s timestamp: %s",name,timestamp);
    }
}
