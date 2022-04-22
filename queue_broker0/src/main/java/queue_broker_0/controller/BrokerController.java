package queue_broker_0.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import queue_broker_0.handler.segment.MessageCreate;
import queue_broker_0.handler.segment.MessageGet;
import queue_broker_0.pojo.message.Message;
import queue_broker_0.pojo.message.MessageOutputInfo;
import queue_broker_0.pojo.user.Offset;
import queue_broker_0.handler.PartitionHandler;
import queue_broker_0.handler.TopicHandler;
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
    private final TopicHandler topicHandler;
    private final PartitionHandler partitionHandler;
    private final OffsetsInfoLoader offsetsInfoLoader;
    private final HashSolver hashSolver;
    private final MessageCreate messageCreate;
    private final MessageGet messageGet;

    public BrokerController(BrokerInfoLoader brokerInfo, TopicHandler topicHandler, PartitionHandler partitionHandler,
                            OffsetsInfoLoader offsetsInfoLoader, HashSolver hashSolver, MessageCreate messageCreateOutput,
                            MessageGet messageGetInput) {
        this.brokerInfo = brokerInfo;
        this.topicHandler = topicHandler;
        this.partitionHandler = partitionHandler;
        this.offsetsInfoLoader = offsetsInfoLoader;
        this.hashSolver = hashSolver;
        this.messageCreate = messageCreateOutput;
        this.messageGet = messageGetInput;
    }

    @GetMapping("/getBrokerInfo")
    public Map<String, Topic> getBrokerInfo() {
        return brokerInfo.getTopics();
    }

    @PostMapping("/create")
    public HttpStatus createTopic(@RequestParam String topicName, @RequestParam String partitionQuantity) {
        try {
            topicHandler.createTopic(topicName);
            partitionHandler.createPartitionsInTopic(topicName, Integer.parseInt(partitionQuantity));
        }
        catch (IOException e) {
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @PostMapping("/addMessage")
    public HttpStatus addMessage(@RequestBody MessageInputInfo message) {
        var result = messageCreate.create(BrokerInfoLoader.LOGS_DIRECTORY_PATH, message);
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
        return messageGet.getData("0.log",messageOutputInfo);           // create handler (method) in further to find the certain segment
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
