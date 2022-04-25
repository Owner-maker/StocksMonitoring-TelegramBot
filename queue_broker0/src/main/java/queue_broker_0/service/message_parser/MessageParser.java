package queue_broker_0.service.message_parser;

import org.springframework.stereotype.Service;
import queue_broker_0.pojo.message.Message;
import queue_broker_0.service.DataProcessor;

import java.sql.Timestamp;
import java.util.regex.Pattern;

@Service
public class MessageParser implements DataProcessor<Message, String> {

    private static final String REGULAR_EXPRESSION_PARSE_MESSAGE = "key: (.*) timestamp: (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}) value: (.*)";
    private static final Pattern PATTERN_PARSE_MESSAGE = Pattern.compile(REGULAR_EXPRESSION_PARSE_MESSAGE);

    @Override
    public Message process(String inputData) {
        var matcher = PATTERN_PARSE_MESSAGE.matcher(inputData);
        return new Message(matcher.group(MessageAttributes.KEY.getGroupNumber()),
                Timestamp.valueOf(matcher.group(MessageAttributes.TIMESTAMP.getGroupNumber())),
                matcher.group(MessageAttributes.VALUE.getGroupNumber()));
    }
}
