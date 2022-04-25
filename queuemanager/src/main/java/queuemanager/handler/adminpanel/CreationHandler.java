package queuemanager.handler.adminpanel;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import queuemanager.handler.adminpanel.model.AdminCommand;
import queuemanager.pojo.BrokerInfoCreation;
import queuemanager.service.broker.BrokerData;
import queuemanager.service.broker.BrokerOutputDataByAPI;
import queuemanager.service.broker.DataOutput;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreationHandler implements AdminCommandHandler{

    private static final AdminCommandType ADMIN_COMMAND_TYPE = AdminCommandType.CREATE;
    private final BrokerData brokerData;
    private final DataOutput<HttpStatus, String, BrokerInfoCreation> brokerOutputData;

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
            brokerAddresses.add(brokerData.getBrokers().stream().
                    filter(broker -> broker.getAddressURL().equals(command.getSingleBrokerAddress())).
                    findFirst().get().getAddressURL());
        }
        else {
            brokerData.getBrokers().forEach(broker -> brokerAddresses.add(broker.getAddressURL()));
        }

        for (String brokerAddress : brokerAddresses) {
            HttpStatus httpStatus = brokerOutputData.create(brokerAddress, new BrokerInfoCreation(command.getTopicName(), command.getPartitionQuantity()));
            if (httpStatus.value() != HttpStatus.OK.value()) {
                result = false;
            }
        }
        return result;
    }
}
