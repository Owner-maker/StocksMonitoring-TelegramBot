package queueapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import queueapplication.exception.ConsumerNotFoundException;
import queueapplication.exception.FileWriteException;
import queueapplication.exception.TopicNotFoundException;
import queueapplication.pojo.Consumer;
import queueapplication.pojo.Message;

import java.io.IOException;

@RestController
@RequestMapping("/queue")
public class ManagerController {

    @Autowired
    private Queue queue;

    @ExceptionHandler({FileWriteException.class, IOException.class})
    @PostMapping("/createTopic")
    public String createTopic(@RequestParam String topicName, @RequestParam String partitions) throws IOException {
        TopicHandler.createTopic(topicName);

        var amountOfPartitions = Integer.parseInt(partitions);
        if(amountOfPartitions !=0){
            PartitionHandler.createPartitionsInTopic(topicName, amountOfPartitions);
        }
        return "OK";
    }

    @PostMapping("/createConsumer")
    public String createConsumer(@RequestParam String userId){
        queue.addConsumer(new Consumer(userId));
        return "OK";
    }

    @PostMapping("/subscribeConsumerToTopic")
    public String subscribeConsumerToTopic(@RequestParam String userId, String topicName){
        queue.getConsumer(userId).updateOffsetInTopic(topicName, Consumer.INITIAL_OFFSET);
        return "OK";
    }

    @PostMapping("/sendMessage")
    public String sendMessageToTopic(@RequestParam String topicName, @RequestParam String userId, @RequestParam String value){
        queue.addMessageToCache(new Message(userId,value,topicName));
        return "OK";
    }

    @ExceptionHandler({ConsumerNotFoundException.class, TopicNotFoundException.class})
    @GetMapping("/getMessage")
    public Message getMessageFromTopic(@RequestParam String topicName, @RequestParam String userId) throws ConsumerNotFoundException, TopicNotFoundException {
        return queue.getMessageFromCache(userId,topicName);
    }
}
