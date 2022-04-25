package queue_broker_0.handler;

import org.springframework.stereotype.Component;
import queue_broker_0.service.BrokerInfoLoader;

import java.io.File;
import java.nio.file.Paths;

@Component
public class TopicCreator implements DataOutput<Boolean, String> {
    @Override
    public Boolean create(String topicName) {
        var pathToTopic = Paths.get(String.format("%s\\%s", BrokerInfoLoader.LOGS_DIRECTORY_PATH, topicName));
        var topicDirectory = new File(String.valueOf(pathToTopic));

        return topicDirectory.mkdir();
    }
}
