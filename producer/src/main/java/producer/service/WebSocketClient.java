package producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;


@Service
public class WebSocketClient {

    public WebSocketClient(){
        var client = new StandardWebSocketClient();

        var stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new StompSessionHandler() {
            @Override
            public void afterConnected(StompSession session, StompHeaders stompHeaders) {
//                session.subscribe("/app/messages",)
            }

            @Override
            public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {

            }

            @Override
            public void handleTransportError(StompSession stompSession, Throwable throwable) {

            }

            @Override
            public Type getPayloadType(StompHeaders stompHeaders) {
                return null;
            }

            @Override
            public void handleFrame(StompHeaders stompHeaders, Object o) {

            }
        };
        stompClient.connect("ws://localhost:9090", sessionHandler);
    }

}
