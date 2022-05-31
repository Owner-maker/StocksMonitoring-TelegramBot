package queuemanager.handler.adminpanel;

import org.springframework.stereotype.Component;
import queuemanager.pojo.AdminCommand;
import queuemanager.pojo.Broker;
import queuemanager.service.broker.BrokerData;

@Component
public class AvailableBrokersHandler implements AdminCommandHandler {

    private static final AdminCommandType ADMIN_COMMAND_TYPE = AdminCommandType.AVAILABLE_BROKERS_ADDRESSES;
    private static final String HTTP_ADDRESS_PATTERN = "http://%s:%s";
    private final BrokerData brokerData;

    public AvailableBrokersHandler(BrokerData brokerData) {
        this.brokerData = brokerData;
    }

    @Override
    public AdminCommandType getAdminCommandHandler() {
        return ADMIN_COMMAND_TYPE;
    }

    @Override
    public boolean handleAdminCommand(AdminCommand command) {
        var brokers = brokerData.getBrokers();
        if (brokers.isEmpty()) {
            System.err.println("There are not any available brokers in cluster");
            return false;
        }

        var builder = new StringBuilder();
        builder.append("\nAvailable brokers:\n");

        for (Broker broker : brokers) {
            builder.append(String.format(HTTP_ADDRESS_PATTERN, broker.getHost(), broker.getPort()));
        }
        System.out.println(builder);

        return true;
    }
}
