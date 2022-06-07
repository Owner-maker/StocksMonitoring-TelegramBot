package producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import producer.pojo.ApplicationType;
import producer.pojo.MessageInputInfo;
import producer.pojo.UserApplication;
import producer.service.MessageSender;

import java.math.BigDecimal;
import java.util.Scanner;

@SpringBootApplication
public class ProducerApplication {


	public static void main(String[] args) throws JsonProcessingException {
		var context = SpringApplication.run(ProducerApplication.class, args);

		MessageSender messageSender = (MessageSender) context.getBean("messageSender");

		Scanner scanner = new Scanner(System.in);
		System.out.println("Topic: ");
		String topicName = scanner.next();
		System.out.println("Key: ");
		String key = scanner.next();
		System.out.println("Your message");
//		String messageValue = scanner.next();

		ObjectMapper objectMapper = new ObjectMapper();

		UserApplication app = new UserApplication("john",
				ApplicationType.SALE, "APL", new BigDecimal(1000), 3);
		var res = objectMapper.writeValueAsString(app);

		System.out.println(messageSender.sendMessage(new MessageInputInfo(key,res,null,topicName,0)));

	}
}
