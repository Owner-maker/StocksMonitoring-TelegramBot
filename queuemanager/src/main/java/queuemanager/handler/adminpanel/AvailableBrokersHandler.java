package queuemanager.handler.adminpanel;

import org.springframework.stereotype.Component;
import queuemanager.handler.adminpanel.model.AdminCommand;
import queuemanager.service.broker.BrokerData;

import java.util.ArrayList;

@Component
public class AvailableBrokersHandler implements AdminCommandHandler{

    private static final AdminCommandType ADMIN_COMMAND_TYPE = AdminCommandType.AVAILABLE_BROKERS_ADDRESSES;
    private BrokerData brokerData;

    public AvailableBrokersHandler(BrokerData brokerData) {
        this.brokerData = brokerData;
    }

    @Override
    public AdminCommandType getAdminCommandHandler() {
        return ADMIN_COMMAND_TYPE;
    }

    @Override
    public boolean handleAdminCommand(AdminCommand command) {
        var brokersAddresses = new ArrayList<String>();
        brokerData.getBrokers().forEach(broker -> brokersAddresses.add(broker.getAddressURL()));

        if (!brokersAddresses.isEmpty()) {
            var builder = new StringBuilder();
            builder.append("\nAvailable brokers:\n");

            for (String brokerAddress : brokersAddresses) {
                builder.append(String.format("%s%n", brokerAddress));
            }
            System.out.println(builder);
        }
        else {
            System.err.println("There are not any available brokers in cluster");
        }

        return true;
    }
}
