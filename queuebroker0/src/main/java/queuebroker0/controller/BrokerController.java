package queuebroker0.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import queuebroker0.handler.partition.PartitionCreator;
import queuebroker0.handler.segment.MessageGetter;
import queuebroker0.handler.segment.MessageSender;
import queuebroker0.handler.topic.TopicCreator;
import queuebroker0.pojo.Broker;
import queuebroker0.pojo.message.Message;
import queuebroker0.pojo.message.MessageInputInfo;
import queuebroker0.pojo.message.MessageOutputInfo;
import queuebroker0.pojo.partition.PartitionInfoCreation;
import queuebroker0.service.BrokerData;
import queuebroker0.service.HashSolver;

import java.util.Optional;

@RestController
public class BrokerController {
    @Value("${server.port}")
    private String brokerPort;
    private static final String BROKER_ADDRESS_LOCALHOST = "http://localhost";

    private final BrokerData brokerData;
    private final TopicCreator topicCreator;
    private final PartitionCreator partitionCreator;
    private final HashSolver hashSolver;
    private final MessageSender messageSender;
    private final MessageGetter messageGetter;

    public BrokerController(BrokerData brokerInfo, TopicCreator topicCreator, PartitionCreator partitionCreator,
                            HashSolver hashSolver, MessageSender messageSender,
                            MessageGetter messageGetter) {
        this.brokerData = brokerInfo;
        this.topicCreator = topicCreator;
        this.partitionCreator = partitionCreator;
        this.hashSolver = hashSolver;
        this.messageSender = messageSender;
        this.messageGetter = messageGetter;
    }

    @GetMapping("/getBrokerInfo")
    public Broker getBrokerData() {
        return new Broker(
                String.format("%s:%s", BROKER_ADDRESS_LOCALHOST, brokerPort),
                brokerData.load()
        );
    }

    @PostMapping("/create")
    public HttpStatus createTopic(@RequestBody PartitionInfoCreation partitionInfoCreation) {
        topicCreator.create(partitionInfoCreation.getTopicName());
        boolean result = partitionCreator.create(partitionInfoCreation);
        return result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @PostMapping("/addMessage")
    public HttpStatus addMessage(@RequestBody MessageInputInfo message) {
        return messageSender.create(message) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @GetMapping("/readMessage")
    public Optional<Message> readMessage(@RequestBody MessageOutputInfo messageOutputInfo) {
        return messageGetter.getData(messageOutputInfo);
    }
}
