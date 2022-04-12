package queueapplication.handler.adminpanel;

import org.springframework.stereotype.Component;
import queueapplication.handler.adminpanel.model.AdminCommand;

import java.util.List;
import java.util.Optional;

@Component
public class AdminCommandFacade {
    private final List<AdminCommandHandler> adminCommandHandlers;

    public AdminCommandFacade(List<AdminCommandHandler> adminCommandHandlers) {
        this.adminCommandHandlers = adminCommandHandlers;
    }

    public boolean processAdminCommand(AdminCommand command) {
        Optional<AdminCommandType> adminCommandType = AdminCommandType.getAdminCommandTypeFromNumber(command.getCommandCode());

        if (adminCommandType.isEmpty()) {
            return false;
        }

        Optional<AdminCommandHandler> commandHandler = adminCommandHandlers.stream().
                filter(adminCommand -> adminCommand.getAdminCommandHandler().equals(adminCommandType.get())).findFirst();

        return commandHandler.map(handler -> handler.handleAdminCommand(command)).orElse(false);
    }
}
