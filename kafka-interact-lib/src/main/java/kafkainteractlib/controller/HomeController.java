package kafkainteractlib.controller;

import kafkainteractlib.service.ConsumerService;
import kafkainteractlib.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    private ProducerService producerService;

    @Autowired
    private ConsumerService consumerService;

    @PostMapping("/sendmessage")
    public String sendMessage(@RequestParam String string){
        producerService.produce(string);
        return "OK";
    }
}
