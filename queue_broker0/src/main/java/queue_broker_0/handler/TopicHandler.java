package queue_broker_0.handler;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import queue_broker_0.exception.FileWriteException;
import queue_broker_0.service.BrokerInfoLoader;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class TopicHandler {

    public void createTopic(String topicName) throws FileWriteException {
        var pathToTopic = Paths.get(String.format("%s\\%s", BrokerInfoLoader.LOGS_DIRECTORY_PATH, topicName));

        if (!Files.exists(pathToTopic)) {
            var topicDirectory = new File(String.valueOf(pathToTopic));

            boolean resultOfCreatingDirectory = topicDirectory.mkdir();
            if (!resultOfCreatingDirectory) {
                throw new FileWriteException("Failed to write the file", topicName);
            }
        }
    }

}
