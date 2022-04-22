package queue_broker_0.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import queue_broker_0.pojo.Partition;
import queue_broker_0.pojo.Topic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                    List<Partition> partitions = new ArrayList<>();
                    Files.list(path).forEach(item -> partitions.add(new Partition(Integer.parseInt(item.getFileName().toString()))));
                    topics.put(path.getFileName().toString(), new Topic(path.getFileName().toString(),partitions
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
