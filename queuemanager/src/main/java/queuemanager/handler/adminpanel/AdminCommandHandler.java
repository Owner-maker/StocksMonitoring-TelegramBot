package queuemanager.handler.adminpanel;

import queuemanager.handler.adminpanel.model.AdminCommand;

public interface AdminCommandHandler {
    AdminCommandType getAdminCommandHandler();

    boolean handleAdminCommand(AdminCommand command);
}
