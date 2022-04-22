package queue_broker_0.service;

import org.springframework.stereotype.Service;
import queue_broker_0.pojo.message.Message;

import java.sql.Timestamp;

@Service
public class MessageParser implements DataProcessor<Message, String>{

    @Override
    public Message process(String inputData) {
        String[] params = inputData.split(" ");
        return new Message(params[1], Timestamp.valueOf(params[3]),params[5]);
    }
}
