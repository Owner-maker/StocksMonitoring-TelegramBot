package queuebroker0.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import queuebroker0.handler.partition.PartitionDataGetter;
import queuebroker0.handler.segment.MessageSender;
import queuebroker0.handler.segment.MessageGetter;
import queuebroker0.pojo.message.Message;
import queuebroker0.pojo.message.MessageOutputInfo;
import queuebroker0.pojo.partition.Partition;
import queuebroker0.pojo.partition.PartitionInfoCreation;
import queuebroker0.pojo.user.Offset;
import queuebroker0.handler.partition.PartitionCreator;
import queuebroker0.handler.topic.TopicCreator;
import queuebroker0.pojo.message.MessageInputInfo;
import queuebroker0.pojo.Topic;
import queuebroker0.service.BrokerData;
import queuebroker0.service.HashSolver;
import queuebroker0.service.OffsetsInfoLoader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BrokerController {

    private final BrokerData brokerInfo;
    private final TopicCreator topicCreator;
    private final PartitionCreator partitionCreator;
    private final OffsetsInfoLoader offsetsInfoLoader;
    private final HashSolver hashSolver;
    private final MessageSender messageSender;
    private final MessageGetter messageGetter;

    public BrokerController(BrokerData brokerInfo, TopicCreator topicCreator, PartitionCreator partitionCreator,
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
        return brokerInfo.load();
    }

    @PostMapping("/create")
    public HttpStatus createTopic(@RequestBody PartitionInfoCreation partitionInfoCreation) {
        var result = topicCreator.create(partitionInfoCreation.getTopicName());
        result = partitionCreator.create(partitionInfoCreation);
        return result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @PostMapping("/addMessage")
    public HttpStatus addMessage(@RequestBody MessageInputInfo message) {
        return messageSender.create(message) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
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
