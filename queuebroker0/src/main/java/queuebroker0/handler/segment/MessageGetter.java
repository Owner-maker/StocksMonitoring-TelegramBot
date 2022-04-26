package queuebroker0.handler.segment;

import org.springframework.stereotype.Component;
import queuebroker0.handler.DataInput;
import queuebroker0.pojo.SegmentInfo;
import queuebroker0.pojo.message.Message;
import queuebroker0.pojo.message.MessageOutputInfo;
import queuebroker0.service.BrokerData;
import queuebroker0.service.DataProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Component
public class MessageGetter implements DataInput<Optional<Message>, MessageOutputInfo> {

    private final DataProcessor<Message, String> messageParser;
    private final DataInput<String, SegmentInfo> segmentNameGetter;

    public MessageGetter(DataProcessor<Message, String> messageParser, DataInput<String, SegmentInfo> segmentNameGetter) {
        this.messageParser = messageParser;
        this.segmentNameGetter = segmentNameGetter;
    }

    @Override
    public Optional<Message> getData(MessageOutputInfo data) {
        String pathToSegment = String.format("%s\\%s\\%d\\%s", BrokerData.LOGS_DIRECTORY_PATH, data.getTopicName(),
                data.getPartitionNumber(), segmentNameGetter.getData(
                        new SegmentInfo(data.getTopicName(),data.getPartitionNumber())));
        try (var fileReader = new FileReader(pathToSegment);
             var bufferedReader = new BufferedReader(fileReader)) {
            var stringMessage = bufferedReader.lines().skip((long) (data.getOffset())).findFirst().orElseThrow();
            return Optional.ofNullable(messageParser.process(stringMessage));
        }
        catch (IOException e) {
            return Optional.empty();
        }
    }
}
