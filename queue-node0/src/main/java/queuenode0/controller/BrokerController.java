package queuenode0.controller;

import org.springframework.web.bind.annotation.*;
import queuenode0.service.Broker;
import queuenode0.service.BrokerInfoLoader;

@RestController
public class BrokerController {

    public Broker broker;

    public BrokerController(Broker broker) {
        this.broker = broker;
    }

    @GetMapping("/getBrokerInfo")
    public Broker getBrokerInfo(){
        return broker;
    }

    @PostMapping("/createTopic")
    public ResponseStatus createTopic(@RequestParam String topicName){
        return null;
    }
}
