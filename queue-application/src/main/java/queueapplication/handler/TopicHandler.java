package queueapplication.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import queueapplication.exception.FileWriteException;
import queueapplication.service.Queue;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class TopicHandler {

    @Autowired
    private static Queue queue;

    private TopicHandler() {
    }

    public static void createTopic(String topicName) throws FileAlreadyExistsException, FileWriteException {
        var pathToTopic = Paths.get(String.format("%s\\%s", QueueHandler.CURRENT_USER_LOGS_DIRECTORY, topicName));

        if (!Files.exists(pathToTopic)) {
            var topicDirectory = new File(String.valueOf(pathToTopic));

            boolean resultOfCreatingDirectory = topicDirectory.mkdir();
            if (!resultOfCreatingDirectory) {
                throw new FileWriteException("Failed to write the file", topicName);
            }
        }
        else {
            throw new FileAlreadyExistsException(topicName);
        }
    }
}
