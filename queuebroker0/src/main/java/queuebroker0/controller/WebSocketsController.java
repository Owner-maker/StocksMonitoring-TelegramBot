package queuebroker0.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketsController {

    @MessageMapping("/messages")
    @SendTo("/topic/messages")
    public void broadcastNews(@Payload String message) {
        System.out.println(message);
    }

}
