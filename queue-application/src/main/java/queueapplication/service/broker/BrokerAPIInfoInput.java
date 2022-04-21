package queueapplication.service.broker;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import queueapplication.pojo.Broker;
import queueapplication.pojo.Topic;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BrokerAPIInfoInput implements DataInput<Optional<List<Broker>>, List<String>> {

    public boolean isBrokerAvailable(String url, int timeout) {
        try {
            var brokerAddressURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) brokerAddressURL.openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_BAD_REQUEST;
        }
        catch (IOException e) {
            return false;
        }
    }

    @Override
    public Optional<List<Broker>> getData(List<String> brokersAddresses) {
        List<Broker> brokers = new ArrayList<>();
        try {
            for (String brokerAddress : brokersAddresses) {
                var restTemplate = new RestTemplate();
                var url = String.format("%s/getBrokerInfo", brokerAddress);
                ResponseEntity<Map<String, Topic>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                        null, new ParameterizedTypeReference<>() {
                        });

                Map<String, Topic> response = responseEntity.getBody();
                brokers.add(new Broker(brokerAddress, response));
            }
        }
        catch (RestClientException e) {
            brokers = null;
        }
        return Optional.ofNullable(brokers);
    }
}
