package queueapplication.controller;

import org.springframework.web.bind.annotation.*;
import queueapplication.handler.LogFileHandler;
import queueapplication.pojo.Message;
import queueapplication.handler.TopicHandler;

@RestController
@RequestMapping("/queue")
public class ClientController {
//    @PostMapping("/init")
//    public UUID initQueue(){
//    }

    @PostMapping("/createtopic")
    public String createTopic(@RequestParam String name){
        TopicHandler.createTopic(name);
        return "OK";
    }

    @PostMapping("/sendmessage")
    public String sendMessageToTopic(@RequestParam String nametopic, @RequestParam String message){
        Message messageToStore = new Message(message,nametopic);
        LogFileHandler.storeMessageInTopic(messageToStore);
        return "OK";
    }

    @GetMapping("/getmessage/{nametopic}")
    public String getMessageFromTopic(@PathVariable String nametopic){
        return null;
    }
}
