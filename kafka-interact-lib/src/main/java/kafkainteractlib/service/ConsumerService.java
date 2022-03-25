package kafkainteractlib.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "testTopic",groupId = "message_group_id")
    public void consume(String string){
        System.out.println(String.format("Consumer: Got the message - %s",string));
    }
}
