package queuemanager.handler.adminpanel;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import queuemanager.pojo.AdminCommand;
import queuemanager.pojo.PartitionInfoCreation;
import queuemanager.pojo.Topic;
import queuemanager.service.broker.DataOutput;
import queuemanager.service.broker.BrokerData;
import queuemanager.service.broker.BrokerOutputDataByAPI;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreationHandler implements AdminCommandHandler {

    private static final AdminCommandType ADMIN_COMMAND_TYPE = AdminCommandType.CREATE;
    private final BrokerData brokerData;
    private final DataOutput<HttpStatus, String, PartitionInfoCreation> brokerOutputData;

    public CreationHandler(BrokerData brokerData, BrokerOutputDataByAPI brokerOutputData) {
        this.brokerData = brokerData;
        this.brokerOutputData = brokerOutputData;
    }

    @Override
    public AdminCommandType getAdminCommandHandler() {
        return ADMIN_COMMAND_TYPE;
    }

    @Override
    public boolean handleAdminCommand(AdminCommand command) {
        List<String> brokerAddresses = new ArrayList<>();
        boolean result = true;

        if (command.isCreatingInSingleBroker()) {
            brokerAddresses.add(brokerData.getBrokers()
                    .stream()
                    .filter(broker -> broker
                            .getAddressURL()
                            .equals(command.getSingleBrokerAddress())).
                    findFirst().get().getAddressURL());
        }
        else {
            brokerData.getBrokers().forEach(broker -> brokerAddresses.add(broker.getAddressURL()));
        }

        final int[] amountOfExistPartitions = {0};
        for (String brokerAddress : brokerAddresses) {

            brokerData.getBrokers()
                    .forEach(broker -> amountOfExistPartitions[0]+=
                            broker.getTopics()
                                    .getOrDefault(command.getTopicName(), new Topic(command.getTopicName(), new ArrayList<>()))
                                    .getPartitions()
                                    .size()
                    );


            HttpStatus httpStatus = brokerOutputData.create(brokerAddress,
                    new PartitionInfoCreation(command.getTopicName(), command.getPartitionQuantity(), amountOfExistPartitions[0]));
            if (httpStatus.value() != HttpStatus.OK.value()) {
                result = false;
            }
        }
        return result;
    }
}
