package queue_broker_0.handler.segment;

import org.springframework.stereotype.Component;
import queue_broker_0.handler.DataOutput;
import queue_broker_0.pojo.SegmentInfo;
import queue_broker_0.pojo.message.MessageInputInfo;
import queue_broker_0.service.BrokerInfoLoader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class MessageSender implements DataOutput<Boolean, MessageInputInfo> {

    private final SegmentNameGetter segmentNameGet;

    public MessageSender(SegmentNameGetter segmentNameGet) {
        this.segmentNameGet = segmentNameGet;
    }

    @Override
    public Boolean create(MessageInputInfo data) {
        String pathToSegment = String.format("%s\\%s\\%d\\%s", BrokerInfoLoader.LOGS_DIRECTORY_PATH,
                data.getTopicName(), data.getPartitionNumber(),
                segmentNameGet.getData(new SegmentInfo(data.getTopicName(),data.getPartitionNumber())));
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
