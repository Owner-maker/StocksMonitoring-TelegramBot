package queue_manager.handler.adminpanel;

import queue_manager.handler.adminpanel.model.AdminCommand;

public interface AdminCommandHandler {
    AdminCommandType getAdminCommandHandler();

    boolean handleAdminCommand(AdminCommand command);
}
