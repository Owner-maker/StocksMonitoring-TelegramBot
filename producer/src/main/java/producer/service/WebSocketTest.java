package producer.service;

import producer.pojo.MessageInputInfo;
import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketTest {
    public static void send(MessageInputInfo messageInputInfo){
        try {
            final WebSocketClientEndpoint clientEndPoint = new WebSocketClientEndpoint(new URI("wss://localhost:8081/websocket/test"));
            clientEndPoint.addMessageHandler(message -> System.out.println(message));

            // send message to websocket
            clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");

            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}
