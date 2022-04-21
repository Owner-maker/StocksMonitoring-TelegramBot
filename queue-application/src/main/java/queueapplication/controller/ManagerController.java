package queueapplication.controller;

import org.springframework.web.bind.annotation.*;
import queueapplication.pojo.Broker;
import queueapplication.service.broker.BrokersInfoLoader;

import java.util.List;

@RestController
@RequestMapping("/queue")
public class ManagerController {

    @GetMapping("/getBrokers")
    public List<Broker> getBrokers(){
        return BrokersInfoLoader.getBrokersInfo();
    }
}
