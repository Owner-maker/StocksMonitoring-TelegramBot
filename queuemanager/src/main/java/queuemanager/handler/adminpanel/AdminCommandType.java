package queuemanager.handler.adminpanel;

import java.util.Arrays;
import java.util.Optional;

public enum AdminCommandType {
    MENU("1"),
    AVAILABLE_BROKERS_ADDRESSES("2"),
    AVAILABLE_CONSUMERS_ADDRESSES("3"),
    CREATE("4");


    private final String commandCode;

    AdminCommandType(String commandCode){
        this.commandCode = commandCode;
    }

    public static Optional<AdminCommandType> getAdminCommandTypeFromNumber(String commandCode){
        return Arrays.stream(AdminCommandType.values()).filter(type -> type.commandCode.equals(commandCode)).findFirst();
    }
}
