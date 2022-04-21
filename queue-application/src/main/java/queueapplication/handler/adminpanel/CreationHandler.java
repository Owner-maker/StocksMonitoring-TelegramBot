package queueapplication.handler.adminpanel;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import queueapplication.handler.adminpanel.model.AdminCommand;
import queueapplication.service.broker.BrokersInfoLoader;

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
                brokerAddresses.add(BrokersInfoLoader.getBrokers().stream().
                        filter(broker -> broker.getAddressURL().equals(command.getSingleBrokerAddress())).findFirst().get().getAddressURL());
                System.out.println(brokerAddresses.get(0));
            }
            else {
                BrokersInfoLoader.getBrokersInfo().forEach(broker -> brokerAddresses.add(broker.getAddressURL()));
            }

            for (String brokerAddress : brokerAddresses) {
                var restTemplate = new RestTemplate();
                var url = String.format(
                            "%s/create?topicName=%s&partitionQuantity=%s", brokerAddress, topicName, partitionQuantity);
                ResponseEntity<Integer> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                        null, new ParameterizedTypeReference<>() {
                        });

                return responseEntity.getStatusCodeValue() == HttpURLConnection.HTTP_OK;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }
}
