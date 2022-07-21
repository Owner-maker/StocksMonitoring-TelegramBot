package queuemanager.pojo;

import queuemanager.service.consumer.ConsumersInputFromXML;

import java.util.ArrayList;
import java.util.List;

public class ConsumerGroup {
    private String groupId;
    private Consumer groupLeader;
    private List<Consumer> consumers;
    private String topicName = ConsumersInputFromXML.BASIC_TOPIC_VALUE;

    public ConsumerGroup() {
        consumers = new ArrayList<>();
    }

    public ConsumerGroup(String groupId, String topicName) {
        this(groupId, topicName, null);
    }

    public ConsumerGroup(String groupId, String topicName, List<Consumer> consumers) {
        this.groupId = groupId;
        this.consumers = consumers;
        this.topicName = topicName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(List<Consumer> consumers) {
        this.consumers = consumers;
    }

    public void addConsumer(Consumer consumer) {
        consumers.add(consumer);
    }

    public Consumer getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(Consumer groupLeader) {
        this.groupLeader = groupLeader;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
