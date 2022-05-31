package consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);

//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            try {
//                System.out.println("Topic name:");
//                String topicName = scanner.next();
//
//                System.out.println("key:");
//                String key = scanner.next();
//
//                System.out.println(messageLoader.getMessage(topicName, key, offset.getOffset()));
//                offset.incrementOffset();
//            }
//            catch (Exception e) {
//                return;
//            }
//
//        }


    }

}
