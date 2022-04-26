package queuebroker0.handler.topic;

import org.springframework.stereotype.Component;
import queuebroker0.handler.DataInput;
import queuebroker0.pojo.Topic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class TopicDataGetter implements DataInput<Map<String, Topic>,String> {
    @Override
    public Map<String, Topic> getData(String pathToTopics) {
        var topics = new HashMap<String, Topic>();
        try (var streamTopicsPaths = Files.list(Paths.get(pathToTopics))) {
            streamTopicsPaths.forEach(path -> topics.put(path.getFileName().toString(),
                    new Topic(path.getFileName().toString())));
            return topics;
        }
        catch (IOException e) {
            return new HashMap<>();
        }
    }
}
