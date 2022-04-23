package producer.service;

import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import producer.pojo.Broker;
import producer.pojo.MessageInputInfo;

import java.net.HttpURLConnection;
import java.sql.Timestamp;


@Component
@Scope("singleton")
public class MessageSender {
    private BrokersInfoLoader loader;
    private HashSolver hashSolver;

    public MessageSender(BrokersInfoLoader loader, HashSolver hashSolver) {
        this.loader = loader;
        this.hashSolver = hashSolver;
    }

    public boolean sendMessage(MessageInputInfo messageInputInfo){
        String brokerAddress = loader.getBrokers().get(0).getAddressURL();
        int partitions = loader.getBrokers().stream().findFirst().orElse(
                new Broker()).getTopics().get(messageInputInfo.getTopicName()).getPartitionQuantity();

        int partitionNumber = hashSolver.getIndex(messageInputInfo.getKey(),partitions);
        messageInputInfo.setTimestamp(new Timestamp(System.currentTimeMillis()));
        messageInputInfo.setPartitionNumber(partitionNumber);
        try {
            var restTemplate = new RestTemplate();
            var url = String.format("%s/addMessage", brokerAddress);
            HttpEntity<MessageInputInfo> request = new HttpEntity<>(messageInputInfo);
            ResponseEntity<Integer> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                    request, new ParameterizedTypeReference<>() {
                    });
            Integer response = responseEntity.getStatusCodeValue();
            return response== HttpURLConnection.HTTP_OK;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
