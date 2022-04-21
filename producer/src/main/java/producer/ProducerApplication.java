package producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import producer.pojo.Message;
import producer.service.BrokersInfoLoader;
import producer.service.HashSolver;
import producer.service.MessageSender;

import java.util.Scanner;

@SpringBootApplication
public class ProducerApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(ProducerApplication.class, args);

		MessageSender messageSender = (MessageSender) context.getBean("messageSender");

		Scanner scanner = new Scanner(System.in);
		System.out.println("Topic: ");
		String topicName = scanner.next();
		System.out.println("Key: ");
		String key = scanner.next();
		System.out.println("Your message");
		String messageValue = scanner.next();

		System.out.println(messageSender.sendMessage(new Message(key,messageValue,null,topicName,0)));

	}
}