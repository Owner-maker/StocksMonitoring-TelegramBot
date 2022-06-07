package queuemanager.handler.adminpanel;

import org.springframework.stereotype.Component;
import queuemanager.pojo.AdminCommand;
import queuemanager.service.consumer.ConsumerData;

@Component
public class AvailableConsumersHandler implements AdminCommandHandler {

    private static final AdminCommandType ADMIN_COMMAND_TYPE = AdminCommandType.AVAILABLE_CONSUMERS_ADDRESSES;
    private static final String HTTP_ADDRESS_PATTERN = "http://%s:%s\n";
    private final ConsumerData consumerData;

    public AvailableConsumersHandler(ConsumerData consumerData) {
        this.consumerData = consumerData;
    }

    @Override
    public AdminCommandType getAdminCommandHandler() {
        return ADMIN_COMMAND_TYPE;
    }

    @Override
    public boolean handleAdminCommand(AdminCommand command) {
        var consumers = consumerData.getConsumerGroups();
        if (consumers.isEmpty()) {
            System.err.println("There are not any available consumers in cluster");
            return false;
        }

        var builder = new StringBuilder();
        builder.append("\nAvailable consumers:\n");

        consumers.forEach(group ->
                group.getConsumers().forEach(consumer ->
                        builder.append(String.format(HTTP_ADDRESS_PATTERN, consumer.getHost(), consumer.getPort()))
                ));
        System.out.println(builder);
        return true;
    }
}