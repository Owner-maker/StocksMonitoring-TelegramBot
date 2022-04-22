package queue_broker_0.handler.segment;

import org.springframework.stereotype.Component;
import queue_broker_0.handler.DataInput;
import queue_broker_0.pojo.message.Message;
import queue_broker_0.pojo.message.MessageOutputInfo;
import queue_broker_0.service.BrokerInfoLoader;
import queue_broker_0.service.DataProcessor;
import queue_broker_0.service.MessageParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Component
public class MessageGet implements DataInput<Optional<Message>, String, MessageOutputInfo> {

    private final DataProcessor<Message, String> messageParser;

    public MessageGet(MessageParser messageParser) {
        this.messageParser = messageParser;
    }

    @Override
    public Optional<Message> getData(String source, MessageOutputInfo data) {
        String pathToSegment = String.format("%s\\%s\\%d\\%s", BrokerInfoLoader.LOGS_DIRECTORY_PATH, data.getTopicName(),
                data.getPartitionNumber(), source);
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