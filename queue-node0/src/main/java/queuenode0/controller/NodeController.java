package queuenode0.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import queuenode0.pojo.Broker;

@RestController
@RequestMapping("/node")
public class NodeController {
    @GetMapping("/getBrokerInfo")
    public Broker getBrokerInfo(){
        return null;
    }
}
