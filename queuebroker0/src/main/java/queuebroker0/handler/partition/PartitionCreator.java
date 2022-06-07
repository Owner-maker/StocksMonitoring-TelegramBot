package queuebroker0.handler.partition;

import org.springframework.stereotype.Component;
import queuebroker0.handler.DataOutput;
import queuebroker0.pojo.SegmentInfo;
import queuebroker0.pojo.partition.PartitionInfoCreation;
import queuebroker0.service.BrokerData;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class PartitionCreator implements DataOutput<Boolean, PartitionInfoCreation> {

    private final DataOutput<Boolean, SegmentInfo> segmentCreator;

    public PartitionCreator(DataOutput<Boolean, SegmentInfo> segmentCreator) {
        this.segmentCreator = segmentCreator;
    }

    @Override
    public Boolean create(PartitionInfoCreation partitionInfoCreation) {
        String topicName = partitionInfoCreation.getTopicName();
        int amountOfExistPartitions = partitionInfoCreation.getAmountOfExistPartitions();
        int partitionQuantity = partitionInfoCreation.getPartitionQuantity();

        var pathToTopic = Paths.get(String.format("%s\\%s", BrokerData.LOGS_DIRECTORY_PATH, topicName));
        boolean result = false;

        if (Files.exists(pathToTopic)) {
            for (int i = amountOfExistPartitions; i < amountOfExistPartitions + partitionQuantity; i++) {
                var partitionDirectory = new File(String.format("%s\\%d", pathToTopic,i));
                result = partitionDirectory.mkdir();
                segmentCreator.create(new SegmentInfo(topicName,i));
            }
        }
        return result;
    }
}
