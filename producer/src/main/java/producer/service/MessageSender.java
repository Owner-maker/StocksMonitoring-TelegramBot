package producer.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import producer.pojo.Broker;
import producer.pojo.MessageInputInfo;

import java.net.HttpURLConnection;
import java.sql.Timestamp;


@Component
public class MessageSender {
    private BrokersInfoLoader loader;
    private HashSolver hashSolver;

    public MessageSender(BrokersInfoLoader loader, HashSolver hashSolver) {
        this.loader = loader;
        this.hashSolver = hashSolver;
    }

    public boolean sendMessage(MessageInputInfo messageInputInfo) {
        String brokerAddress = loader.getBrokers().get(0).getAddressURL();
        int partitions = loader.getBrokers().stream()
                .findFirst()
                .orElse(new Broker())
                .getTopics()
                .get(messageInputInfo.getTopicName())
                .getPartitions()
                .size();

        int partitionNumber = hashSolver.getIndex(messageInputInfo.getKey(), partitions);
        messageInputInfo.setTimestamp(new Timestamp(System.currentTimeMillis()));
        messageInputInfo.setPartitionNumber(partitionNumber);
        try {
            var restTemplate = new RestTemplate();
            var url = String.format("%s/addMessage", brokerAddress);
            HttpEntity<MessageInputInfo> request = new HttpEntity<>(messageInputInfo);
            ResponseEntity<HttpStatus> response = restTemplate.exchange(url, HttpMethod.POST,
                    request, new ParameterizedTypeReference<>() {
                    });
            return response.getStatusCode() == HttpStatus.OK;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
