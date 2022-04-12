package queueapplication.handler.adminpanel;

import queueapplication.handler.adminpanel.model.AdminCommand;

public interface AdminCommandHandler {
    AdminCommandType getAdminCommandHandler();
    boolean handleAdminCommand(AdminCommand command);
}
