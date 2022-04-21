package producer.service;

import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import producer.pojo.Message;

import java.net.HttpURLConnection;
import java.sql.Timestamp;
import java.util.Map;


@Component
@Scope("singleton")
public class MessageSender {
    private BrokersInfoLoader loader;
    private HashSolver hashSolver;

    public MessageSender(BrokersInfoLoader loader, HashSolver hashSolver) {
        this.loader = loader;
        this.hashSolver = hashSolver;
    }

    public boolean sendMessage(Message message){
        String brokerAddress = loader.getBrokers().get(0).getAddressURL();
        int partitions = loader.getBrokers().stream().findFirst().get().getTopics().get(message.getTopicName()).getPartitionQuantity();

        int partitionNumber = hashSolver.getIndex(message.getKey(),partitions);
        Message messageToSend = new Message(message.getKey(), message.getValue(),new Timestamp(System.nanoTime()), message.getTopicName(), partitionNumber);
        try {
            var restTemplate = new RestTemplate();
            var url = String.format("%s/addMessage", brokerAddress);
            HttpEntity<Message> request = new HttpEntity<>(messageToSend);
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
