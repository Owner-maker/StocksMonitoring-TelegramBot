package queuebroker0.handler.topic;

import org.springframework.stereotype.Component;
import queuebroker0.handler.DataOutput;
import queuebroker0.service.BrokerData;

import java.io.File;
import java.nio.file.Paths;

@Component
public class TopicCreator implements DataOutput<Boolean, String> {
    @Override
    public Boolean create(String topicName) {
        var pathToTopic = Paths.get(String.format("%s\\%s", BrokerData.LOGS_DIRECTORY_PATH, topicName));
        var topicDirectory = new File(String.valueOf(pathToTopic));

        if(!topicDirectory.exists()){
            return topicDirectory.mkdir();
        }
        return false;
    }
}
