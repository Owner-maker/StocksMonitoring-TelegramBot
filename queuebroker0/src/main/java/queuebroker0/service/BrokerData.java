package queuebroker0.service;

import org.springframework.stereotype.Service;
import queuebroker0.handler.DataInput;
import queuebroker0.pojo.partition.Partition;
import queuebroker0.pojo.Topic;

import java.util.List;
import java.util.Map;

@Service
public final class BrokerData implements DataLoad<Map<String, Topic>> {
    public static final String LOGS_DIRECTORY_PATH = String.format("%s%s", System.getProperty("user.dir"), "\\src\\main\\resources\\static\\logs");


    private final DataInput<Map<String, Topic>, String> topicDataGetter;
    private final DataInput<List<Partition>, String> partitionDataGetter;
//    private final Map<String, Topic> topics;

    public BrokerData(DataInput<Map<String, Topic>, String> topicDataGetter,
                      DataInput<List<Partition>, String> partitionDataGetter) {
        this.topicDataGetter = topicDataGetter;
        this.partitionDataGetter = partitionDataGetter;
//        topics = topicDataGetter.getData(LOGS_DIRECTORY_PATH);
//        topics.values().forEach(topic -> topic.setPartitions(
//                partitionDataGetter.getData(String.format("%s%s", LOGS_DIRECTORY_PATH, topic.getName()))));
    }

//    public Map<String, Topic> getData() {
//        return new HashMap<>(topics);
//    }

    @Override
    public Map<String, Topic> load() {
        var topicsToReturn = topicDataGetter.getData(LOGS_DIRECTORY_PATH);
        topicsToReturn.values().forEach(topic -> topic.setPartitions(
                partitionDataGetter.getData(String.format("%s\\%s", LOGS_DIRECTORY_PATH, topic.getName()))
                )
        );
        return topicsToReturn;
    }
}
