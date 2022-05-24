package queuemanager.handler.adminpanel;

import queuemanager.pojo.AdminCommand;

public interface AdminCommandHandler {
    AdminCommandType getAdminCommandHandler();

    boolean handleAdminCommand(AdminCommand command);
}
