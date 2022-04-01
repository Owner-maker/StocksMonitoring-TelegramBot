package queueapplication.pojo;

import java.util.HashMap;
import java.util.Map;

public class Consumer {
    public static final int INITIAL_OFFSET = 0;

    private final String id;
    private final HashMap<String, Integer> offsetsMapByTopics = new HashMap<>();

    public Consumer(String id) {
        this.id = id;
    }

    public Consumer(String id, String topicName){
        this(id);
        updateOffsetInTopic(topicName,INITIAL_OFFSET);
    }

    public String getId() {
        return id;
    }

    public int getOffsetByTopic(String topicName){
        return offsetsMapByTopics.get(topicName);
    }

    public Map<String, Integer> getOffsetsMapByTopics() {
        return new HashMap<>(offsetsMapByTopics);
    }

    public void updateOffsetInTopic(String topicName,int newOffset){
        offsetsMapByTopics.put(topicName,newOffset);
    }
}
