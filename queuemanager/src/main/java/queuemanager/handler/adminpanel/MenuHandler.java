package queuemanager.handler.adminpanel;

import org.springframework.stereotype.Component;
import queuemanager.pojo.AdminCommand;

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
                .append("0 -> to stop console\n")
                .append("1 -> to print menu\n")
                .append("2 -> to show available brokers\n")
                .append("3 -> to show available consumers\n")
                .append("4 -> to create topic and partitions\n")
                .append("---------------------------------");
        System.out.println(builder);
        return true;
    }
}
