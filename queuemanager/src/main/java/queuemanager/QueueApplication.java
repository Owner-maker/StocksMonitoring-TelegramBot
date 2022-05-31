package queuemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import queuemanager.pojo.AdminCommand;
import queuemanager.service.admin_panel.AdminPanelService;

import java.util.Scanner;

@SpringBootApplication
public class QueueApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(QueueApplication.class, args);

        var service = (AdminPanelService) context.getBean("adminPanelService");
        service.getCommandFacade().processAdminCommand(new AdminCommand("1"));

        var scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.next();
            if (command.equals("0")){
                System.out.println("- close admin panel -");
                break;
            }
            service.processCommandFacade(command);
            scanner.reset();
        }
    }
}

