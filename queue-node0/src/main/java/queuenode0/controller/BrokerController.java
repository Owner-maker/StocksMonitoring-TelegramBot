package queuenode0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import queuenode0.handler.PartitionHandler;
import queuenode0.handler.SegmentHandler;
import queuenode0.handler.TopicHandler;
import queuenode0.pojo.Message;
import queuenode0.pojo.user.Offset;
import queuenode0.pojo.Topic;
import queuenode0.service.BrokerInfoLoader;
import queuenode0.service.HashSolver;
import queuenode0.service.OffsetsInfoLoader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

@RestController
public class BrokerController {

    private BrokerInfoLoader brokerInfo;
    private TopicHandler topicHandler;
    private PartitionHandler partitionHandler;
    private SegmentHandler segmentHandler;
    private OffsetsInfoLoader offsetsInfoLoader;
    private HashSolver hashSolver;

    public BrokerController(BrokerInfoLoader brokerInfo, TopicHandler topicHandler, PartitionHandler partitionHandler,
                            SegmentHandler segmentHandler, OffsetsInfoLoader offsetsInfoLoader, HashSolver hashSolver) {
        this.brokerInfo = brokerInfo;
        this.topicHandler = topicHandler;
        this.partitionHandler = partitionHandler;
        this.segmentHandler = segmentHandler;
        this.offsetsInfoLoader = offsetsInfoLoader;
        this.hashSolver = hashSolver;
    }

    @GetMapping("/getBrokerInfo")
    public Map<String,Topic> getBrokerInfo(){
        return brokerInfo.getTopics();
    }

    @PostMapping("/create")
    public HttpStatus createTopic(@RequestParam String topicName, @RequestParam String partitionQuantity){
        try{
            topicHandler.createTopic(topicName);
            partitionHandler.createPartitionsInTopic(topicName,Integer.parseInt(partitionQuantity));
        }
        catch (IOException e){
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @PostMapping("/addMessage")
    public HttpStatus addMessage(@RequestBody Message message){
        try{
            segmentHandler.addMessage(message);
        }
        catch(Exception e){
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @GetMapping("/getOffsets")
    public List<Offset> getOffsets(@RequestParam String groupId){
        return offsetsInfoLoader.getOffsets().get(groupId);
    }

    @GetMapping("/readMessage")
    public String readMessage(@RequestParam String topicName, @RequestParam String key,@RequestParam String offset){
        int partitionQuantity = partitionHandler.getAmountOfExistPartitions(topicName);
        int partitionNumber = hashSolver.getIndex(key,partitionQuantity);
        return segmentHandler.getMessage(topicName,partitionNumber,Integer.parseInt(offset));
    }

    @PostMapping("/incrementOffset")
    public int increaseOffset(@RequestParam String topicName,@RequestParam String groupId){
        try{
            offsetsInfoLoader.incrementOffsetOfGroup(topicName, groupId);
            System.err.println(String.format("New offset of TestGroupId: %s",offsetsInfoLoader.getOffsets().get("TestGroupId").get(0).getOffset()));
            return HttpURLConnection.HTTP_OK;
        }
        catch(Exception e){
            return HttpURLConnection.HTTP_INTERNAL_ERROR;
        }
    }
}
