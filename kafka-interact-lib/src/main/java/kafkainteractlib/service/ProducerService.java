package kafkainteractlib.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("testTopic") .partitions(1).replicas(1) .build();
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void produce(String string){
        kafkaTemplate.send("testTopic",string);
    }
}
