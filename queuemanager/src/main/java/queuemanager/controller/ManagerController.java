package queuemanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import queuemanager.pojo.Broker;
import queuemanager.service.broker.BrokerData;

import java.util.List;

@RestController
@RequestMapping("/queue")
public class ManagerController {

    private final BrokerData brokerData;

    public ManagerController(BrokerData brokerData) {
        this.brokerData = brokerData;
    }

    @GetMapping("/getBrokers")
    public List<Broker> getBrokers(){
        return brokerData.getBrokers();
    }
}
