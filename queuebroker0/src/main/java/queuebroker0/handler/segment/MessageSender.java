package queuebroker0.handler.segment;

import org.springframework.stereotype.Component;
import queuebroker0.handler.DataInput;
import queuebroker0.handler.DataOutput;
import queuebroker0.pojo.SegmentInfo;
import queuebroker0.pojo.message.MessageInputInfo;
import queuebroker0.service.BrokerData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class MessageSender implements DataOutput<Boolean, MessageInputInfo> {

    private final DataInput<String, SegmentInfo> segmentNameGetter;

    public MessageSender(DataInput<String, SegmentInfo> segmentNameGetter) {
        this.segmentNameGetter = segmentNameGetter;
    }

    @Override
    public Boolean create(MessageInputInfo data) {
        String pathToSegment = String.format("%s\\%s\\%d\\%s", BrokerData.LOGS_DIRECTORY_PATH,
                data.getTopicName(), data.getPartitionNumber(),
                segmentNameGetter.getData(new SegmentInfo(data.getTopicName(),data.getPartitionNumber())));
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
