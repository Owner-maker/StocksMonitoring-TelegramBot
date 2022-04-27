package producer.service;

import producer.pojo.MessageInputInfo;

import javax.websocket.*;
import java.net.URI;
import java.nio.ByteBuffer;

@ClientEndpoint
public class WebSocketClientEndpoint {

    Session userSession = null;
    private MessageHandler messageHandler;

    public WebSocketClientEndpoint(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("opening websocket");
        this.userSession = userSession;
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("closing websocket");
        this.userSession = null;
    }

    @OnMessage
    public void onMessage(String message) {
//        if (this.messageHandler != null) {
//            this.messageHandler.handleMessage(message);
//        }
    }

    @OnMessage
    public void onMessage(ByteBuffer bytes) {
        System.out.println("Handle byte buffer");
    }


    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    public void sendMessage(MessageInputInfo message) {
        this.userSession.getAsyncRemote().sendObject(message);
    }

    public static interface MessageHandler {

        public void handleMessage(String message);
    }

}



