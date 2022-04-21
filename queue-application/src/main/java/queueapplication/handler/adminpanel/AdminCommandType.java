package queueapplication.handler.adminpanel;

import java.util.Arrays;
import java.util.Optional;

public enum AdminCommandType {
    MENU("menu"),
    AVAILABLE_BROKERS_ADDRESSES("brokers"),
    CREATE("create");


    private final String commandCode;

    AdminCommandType(String commandCode){
        this.commandCode = commandCode;
    }

    public static Optional<AdminCommandType> getAdminCommandTypeFromNumber(String commandCode){
        return Arrays.stream(AdminCommandType.values()).filter(type -> type.commandCode.equals(commandCode)).findFirst();
    }
}
