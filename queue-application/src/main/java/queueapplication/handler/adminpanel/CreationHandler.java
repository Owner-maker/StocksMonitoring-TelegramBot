package queueapplication.handler.adminpanel;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import queueapplication.handler.adminpanel.model.AdminCommand;
import queueapplication.service.BrokersInfoLoader;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreationHandler implements AdminCommandHandler {

    private static final AdminCommandType ADMIN_COMMAND_TYPE = AdminCommandType.CREATE;

    @Override
    public AdminCommandType getAdminCommandHandler() {
        return ADMIN_COMMAND_TYPE;
    }


    @Override
    public boolean handleAdminCommand(AdminCommand command) {
        try {
            List<String> brokerAddresses = new ArrayList<>();
            int partitionQuantity = command.getPartitionQuantity();
            String topicName = command.getTopicName();


            if (command.isCreatingInSingleBroker()) {
                brokerAddresses.add(BrokersInfoLoader.getBrokersAddresses().stream().
                        filter(address -> address.equals(command.getSingleBrokerAddress())).findFirst().orElseThrow());
            }
            else {
                brokerAddresses = BrokersInfoLoader.getBrokersAddresses();
            }

            for (String brokerAddress : brokerAddresses) {
                var restTemplate = new RestTemplate();
                var url = String.format(
                        "%s/createTopicAndPartitions?topicName=%s&partitionQuantity=%s", brokerAddress, topicName, partitionQuantity);
                ResponseEntity<Integer> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                        null, new ParameterizedTypeReference<>() {
                        });

                return responseEntity.getStatusCodeValue() == HttpURLConnection.HTTP_OK;
            }
        }
        catch (Exception e) {
            return false;
        }

        return false;
    }
}
