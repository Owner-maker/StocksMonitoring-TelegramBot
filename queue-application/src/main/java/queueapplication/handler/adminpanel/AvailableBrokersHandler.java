package queueapplication.handler.adminpanel;

import org.springframework.stereotype.Component;
import queueapplication.handler.adminpanel.model.AdminCommand;
import queueapplication.service.BrokersInfoLoader;

import java.util.List;

@Component
public class AvailableBrokersHandler implements AdminCommandHandler{

    private static final AdminCommandType ADMIN_COMMAND_TYPE = AdminCommandType.SHOW_AVAILABLE_BROKERS_ADDRESSES;

    @Override
    public AdminCommandType getAdminCommandHandler() {
        return ADMIN_COMMAND_TYPE;
    }

    @Override
    public boolean handleAdminCommand(AdminCommand command) {
        List<String> brokersAddresses = BrokersInfoLoader.getBrokersAddresses();
        if(!brokersAddresses.isEmpty()){
            var builder = new StringBuilder();
            builder.append("\nAvailable brokers:\n");

            for(String brokerAddress: brokersAddresses){
                builder.append(String.format("%s%n",brokerAddress));
            }
            System.out.println(builder);
        }
        else{
            System.err.println("There are not any available brokers in cluster");
        }

        return true;
    }
}
