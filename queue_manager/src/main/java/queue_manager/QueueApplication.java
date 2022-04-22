package queue_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import queue_manager.handler.adminpanel.model.AdminCommand;
import queue_manager.service.admin_panel.AdminPanelService;

import java.util.Scanner;

@SpringBootApplication
public class QueueApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(QueueApplication.class, args);

        var service = (AdminPanelService) context.getBean("adminPanelService");
        service.getCommandFacade().processAdminCommand(new AdminCommand("menu"));


        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();
        service.processCommandFacade(command);
    }
}
