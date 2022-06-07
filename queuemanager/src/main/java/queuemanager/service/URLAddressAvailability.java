package queuemanager.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.*;

@Service
public class URLAddressAvailability {
    public static final int DEFAULT_TIMEOUT = 3000;

    public boolean isAddressAvailable(String host, int port, int timeout) {
        try (var socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeout);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
