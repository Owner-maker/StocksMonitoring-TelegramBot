package queue_broker_0.handler;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import queue_broker_0.pojo.SegmentInfoCreation;
import queue_broker_0.service.BrokerInfoLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class PartitionHandler {

    private final DataOutput<Boolean,String, SegmentInfoCreation> segmentCreator;

    public PartitionHandler(DataOutput<Boolean, String, SegmentInfoCreation> segmentCreator) {
        this.segmentCreator = segmentCreator;
    }

    public void createPartitionsInTopic(String topicName, int amountOfPartitions) throws IOException {
        var pathToTopic = Paths.get(String.format("%s\\%s", BrokerInfoLoader.LOGS_DIRECTORY_PATH, topicName));

        if (Files.exists(pathToTopic)) {
            int amountOfExistPartitions = getAmountOfExistPartitions(topicName);

            for (int i = amountOfExistPartitions; i < amountOfExistPartitions + amountOfPartitions; i++) {
                var partitionDirectory = new File(String.format("%s\\%d",String.valueOf(pathToTopic),i));
                partitionDirectory.mkdir();      //     ------ handle the possible exceptions in further ------
                segmentCreator.create(BrokerInfoLoader.LOGS_DIRECTORY_PATH,new SegmentInfoCreation(topicName,amountOfPartitions));
            }
        }
    }

    public int getAmountOfExistPartitions(String topicName){
        var pathToTopic = Paths.get(String.format("%s\\%s", BrokerInfoLoader.LOGS_DIRECTORY_PATH, topicName));
        try(Stream<Path> stream = Files.list(pathToTopic)){
            return (int)stream.count();
        }
        catch (IOException e){
            return 0;
        }
    }
}
