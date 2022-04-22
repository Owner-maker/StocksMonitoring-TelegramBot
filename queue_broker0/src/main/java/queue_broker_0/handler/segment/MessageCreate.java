package queue_broker_0.handler.segment;

import org.springframework.stereotype.Component;
import queue_broker_0.handler.DataOutput;
import queue_broker_0.pojo.message.MessageInputInfo;
import queue_broker_0.service.BrokerInfoLoader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class MessageCreate implements DataOutput<Boolean, String, MessageInputInfo> {
    @Override
    public Boolean create(String destination, MessageInputInfo data) {
        String topicName = data.getTopicName();
        int partitionNumber = data.getPartitionNumber();
        String pathToSegment = String.format("%s\\%s\\%d\\%s", BrokerInfoLoader.LOGS_DIRECTORY_PATH, topicName, partitionNumber,destination);
        try (var writer = new FileWriter(pathToSegment, true);
             var bufferedWriter = new BufferedWriter(writer)) {

            bufferedWriter.write(data.toString());
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }
}
