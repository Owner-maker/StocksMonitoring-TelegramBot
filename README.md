# StocksMonitoring-TelegramBot

*Technologies:*  
-Java 11  
-Spring Boot  
-Spring Data  
-Maven  
-MySQL  
-Kafka  
-Docker?  
-Zookeeper?  
  
-Telegram API  
-YH Finance API  
  
*Project components:*  
-Producer -> YH Finance API  
-Database -> Store Users info + subscriptions  
-Queue using Kafka -> Store and Getting info about stocks etc  
-Library -> Connect to Queue, sending, getting messages  
-Consumer -> Telegram GUI client (Telegram Bot)  
