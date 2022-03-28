package queueapplication.handler;

import queueapplication.pojo.Topic;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TopicHandler {
    public static final String CURRENT_USER_TOPICS_DIRECTORY = String.format("%s%s", System.getProperty("user.dir"), "\\src\\main\\resources\\static\\topics");
    public static final String BASIC_LOG_FILE_NAME_OF_TOPIC = "0.log";

    public static void createTopic(String name) {
        var pathToTopic = Paths.get(String.format("%s\\%s", CURRENT_USER_TOPICS_DIRECTORY, name));

        if (!Files.exists(pathToTopic)) {
            File topicDirectory = new File(String.valueOf(pathToTopic));
            File logFileOfTopic = new File(String.format("%s\\%s", pathToTopic, BASIC_LOG_FILE_NAME_OF_TOPIC));
            try {
                topicDirectory.mkdir();
                logFileOfTopic.createNewFile();
            }
            catch (Exception e) {
                System.err.println(String.format("Topic %s was not created", name));
            }
        }

    }
}
