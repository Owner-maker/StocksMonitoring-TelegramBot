package queuebroker0.handler;

import org.springframework.stereotype.Component;
import queuebroker0.pojo.SegmentInfo;
import queuebroker0.pojo.partition.PartitionInfoCreation;
import queuebroker0.service.BrokerInfoLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class PartitionCreator implements DataOutput<Boolean, String, PartitionInfoCreation> {

    private final DataOutput<Boolean,String, SegmentInfo> segmentCreator;

    public PartitionCreator(DataOutput<Boolean, String, SegmentInfo> segmentCreator) {
        this.segmentCreator = segmentCreator;
    }

    public void createPartitionsInTopic(String topicName, int amountOfPartitions) throws IOException {
        var pathToTopic = Paths.get(String.format("%s\\%s", BrokerInfoLoader.LOGS_DIRECTORY_PATH, topicName));

        if (Files.exists(pathToTopic)) {
            int amountOfExistPartitions = getAmountOfExistPartitions(topicName);

            for (int i = amountOfExistPartitions; i < amountOfExistPartitions + amountOfPartitions; i++) {
                var partitionDirectory = new File(String.format("%s\\%d",String.valueOf(pathToTopic),i));
                partitionDirectory.mkdir();      //     ------ handle the possible exceptions in further ------
                segmentCreator.create(BrokerInfoLoader.LOGS_DIRECTORY_PATH,new SegmentInfo(topicName,amountOfPartitions));
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

    @Override
    public Boolean create(String destination, PartitionInfoCreation data) {
        return null;
    }
}
