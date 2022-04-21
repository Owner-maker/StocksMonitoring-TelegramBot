package telegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.bots.TelegramWebhookBot;

@SpringBootApplication
public class TelegrambotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegrambotApplication.class, args);
		TelegramWebhookBot
	}

}
