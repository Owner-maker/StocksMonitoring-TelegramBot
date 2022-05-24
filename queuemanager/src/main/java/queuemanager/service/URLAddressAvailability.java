package queuemanager.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class URLAddressAvailability {
    public static final int DEFAULT_TIMEOUT = 5000;

    public boolean isAddressAvailable(String url, int timeout) {
        try {
            var brokerAddressURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) brokerAddressURL.openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return responseCode >= HttpURLConnection.HTTP_OK && responseCode <= HttpURLConnection.HTTP_NOT_FOUND;
        }
        catch (IOException e) {
            return false;
        }
    }
}
