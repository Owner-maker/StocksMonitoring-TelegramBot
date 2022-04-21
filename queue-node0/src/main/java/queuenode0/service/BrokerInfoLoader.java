package queuenode0.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import queuenode0.pojo.Topic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope("singleton")
public final class BrokerInfoLoader {
    public static final String LOGS_DIRECTORY_PATH = String.format("%s%s", System.getProperty("user.dir"), "\\src\\main\\resources\\static\\logs");

    private final Map<String, Topic> topics;

    public BrokerInfoLoader() {
        topics = loadInfoAboutTopics();
    }

    public Map<String, Topic> getTopics() {
        return topics;
    }

    public static Map<String, Topic> loadInfoAboutTopics() {
        var topics = new HashMap<String, Topic>();
        try {
            Files.list(Paths.get(LOGS_DIRECTORY_PATH)).forEach(path ->
            {
                try {
                    topics.put(path.getFileName().toString(), new Topic(path.getFileName().toString(),
                            (int) Files.list(path).count()
                    ));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        catch (IOException e) {
        }
        return topics;
    }
}
