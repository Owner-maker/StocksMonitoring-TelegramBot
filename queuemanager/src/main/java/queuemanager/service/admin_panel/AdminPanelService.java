package queuemanager.service.admin_panel;

import org.springframework.stereotype.Service;
import queuemanager.pojo.AdminCommand;
import queuemanager.handler.adminpanel.AdminCommandFacade;

import java.util.Scanner;

@Service
public final class AdminPanelService {

    private final AdminCommandFacade commandFacade;

    public AdminPanelService(AdminCommandFacade commandFacade) {
        this.commandFacade = commandFacade;
    }

    public AdminCommandFacade getCommandFacade() {
        return commandFacade;
    }

    public void processCommandFacade(String commandCode) {
        switch (commandCode) {
            case "menu":
            case "brokers":
                commandFacade.processAdminCommand(new AdminCommand(commandCode));
                break;
            case "create":
                Scanner scanner = new Scanner(System.in);

                System.out.println("topic name: ");
                String topicName = scanner.next();

                System.out.println("create in whole cluster (c) or in certain broker (b)?: ");
                String placeCreation = scanner.next();
                boolean isCreatingInSingleBroker = false;
                String singleBrokerAddress = null;

                if(placeCreation.equals("b")){
                    isCreatingInSingleBroker = true;
                    System.out.println("broker address: ");
                    singleBrokerAddress = scanner.next();
                }

                System.out.println("partition quantity: ");
                int partitionQuantity = scanner.nextInt();

                var result = commandFacade.processAdminCommand(new AdminCommand(commandCode,topicName,isCreatingInSingleBroker,
                        partitionQuantity,singleBrokerAddress));
                if (result){
                    System.out.println("Success");
                }else{
                    System.err.println("Some troubles");
                }
                break;
            default:
                System.err.println("Wrong command code");
        }
    }
}
