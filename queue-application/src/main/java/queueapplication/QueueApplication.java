package queueapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import queueapplication.service.BrokersInfo;

@SpringBootApplication
public class QueueApplication {
	public static void main(String[] args) {
		SpringApplication.run(QueueApplication.class, args);
//
//		BrokersInfo brokersInfo = new BrokersInfo();
//		System.err.println(brokersInfo.getList().size());

	}
}
