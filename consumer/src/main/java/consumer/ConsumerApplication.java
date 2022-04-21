package consumer;

import consumer.pojo.Offset;
import consumer.service.MessageLoader;
import consumer.service.OffsetsLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(ConsumerApplication.class, args);

        OffsetsLoader loader = (OffsetsLoader) context.getBean("offsetsLoader");
        MessageLoader messageLoader = (MessageLoader) context.getBean("messageLoader");
        Offset offset = loader.getUsers().get("TestGroupId").get(0);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Topic name:");
                String topicName = scanner.next();

                System.out.println("key:");
                String key = scanner.next();

                System.out.println(messageLoader.getMessage(topicName, key, offset.getOffset()));
                offset.incrementOffset();
            }
            catch (Exception e) {
                return;
            }

        }


    }

}
