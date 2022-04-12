package queueapplication.handler.adminpanel;

import org.springframework.stereotype.Component;
import queueapplication.handler.adminpanel.model.AdminCommand;

@Component
public class MenuHandler implements AdminCommandHandler {

    private static final AdminCommandType ADMIN_COMMAND_TYPE = AdminCommandType.MENU;

    @Override
    public AdminCommandType getAdminCommandHandler() {
        return ADMIN_COMMAND_TYPE;
    }

    @Override
    public boolean handleAdminCommand(AdminCommand command) {
        StringBuilder builder = new StringBuilder();

        builder.append("\n---------------------------------\n")
                .append("Print the menu item:\n")
                .append("---------------------------------\n")
                .append("menu -> to print menu\n")
                .append("create -> to create topic and partitions\n")
                .append("brokers -> to show available brokers\n")
                .append("---------------------------------\n");
        System.out.println(builder);
        return true;
    }
}
