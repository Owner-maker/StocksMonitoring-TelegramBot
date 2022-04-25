package queue_broker_0.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import queue_broker_0.handler.segment.MessageSender;
import queue_broker_0.handler.segment.MessageGetter;
import queue_broker_0.pojo.message.Message;
import queue_broker_0.pojo.message.MessageOutputInfo;
import queue_broker_0.pojo.user.Offset;
import queue_broker_0.handler.PartitionCreator;
import queue_broker_0.handler.TopicCreator;
import queue_broker_0.pojo.message.MessageInputInfo;
import queue_broker_0.pojo.Topic;
import queue_broker_0.service.BrokerInfoLoader;
import queue_broker_0.service.HashSolver;
import queue_broker_0.service.OffsetsInfoLoader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BrokerController {

    private final BrokerInfoLoader brokerInfo;
    private final TopicCreator topicCreator;
    private final PartitionCreator partitionCreator;
    private final OffsetsInfoLoader offsetsInfoLoader;
    private final HashSolver hashSolver;
    private final MessageSender messageSender;
    private final MessageGetter messageGetter;

    public BrokerController(BrokerInfoLoader brokerInfo, TopicCreator topicCreator, PartitionCreator partitionCreator,
                            OffsetsInfoLoader offsetsInfoLoader, HashSolver hashSolver, MessageSender messageSender,
                            MessageGetter messageGetter) {
        this.brokerInfo = brokerInfo;
        this.topicCreator = topicCreator;
        this.partitionCreator = partitionCreator;
        this.offsetsInfoLoader = offsetsInfoLoader;
        this.hashSolver = hashSolver;
        this.messageSender = messageSender;
        this.messageGetter = messageGetter;
    }

    @GetMapping("/getBrokerInfo")
    public Map<String, Topic> getBrokerInfo() {
        return brokerInfo.getTopics();
    }

    @PostMapping("/create")
    public HttpStatus createTopic(@RequestParam String topicName, @RequestParam String partitionQuantity) {
        try {
            topicCreator.create(topicName);
            partitionCreator.createPartitionsInTopic(topicName, Integer.parseInt(partitionQuantity));
        }
        catch (IOException e) {
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @PostMapping("/addMessage")
    public HttpStatus addMessage(@RequestBody MessageInputInfo message) {
        var result = messageSender.create(message);
        if(!result){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @GetMapping("/getOffsets")
    public List<Offset> getOffsets(@RequestParam String groupId) {
        return offsetsInfoLoader.getOffsets().get(groupId);
    }

    @GetMapping("/readMessage")
    public Optional<Message> readMessage(@RequestBody MessageOutputInfo messageOutputInfo) {
        return messageGetter.getData(messageOutputInfo);
    }

    @PostMapping("/incrementOffset")
    public int increaseOffset(@RequestParam String topicName, @RequestParam String groupId) {
        try {
            offsetsInfoLoader.incrementOffsetOfGroup(topicName, groupId);
            System.err.println(String.format("New offset of TestGroupId: %s", offsetsInfoLoader.getOffsets().get("TestGroupId").get(0).getOffset()));
            return HttpURLConnection.HTTP_OK;
        }
        catch (Exception e) {
            return HttpURLConnection.HTTP_INTERNAL_ERROR;
        }
    }
}
