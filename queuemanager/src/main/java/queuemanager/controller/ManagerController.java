package queuemanager.controller;

import org.springframework.web.bind.annotation.*;
import queuemanager.pojo.Broker;
import queuemanager.service.broker.BrokerData;

import java.util.List;

@RestController
@RequestMapping("/queue")
public class ManagerController {

    private BrokerData brokerData;

    public ManagerController(BrokerData brokerData) {
        this.brokerData = brokerData;
    }

    @GetMapping("/getBrokers")
    public List<Broker> getBrokers(){
        return brokerData.getBrokers();
    }
}
