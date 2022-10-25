# StocksMonitoring-TelegramBot

*Technologies:*  
-Java 11  
-Spring Boot  
-Spring Data  
-Maven  
-MS SQL
  
-Telegram API    
  
*Project components:*  
-TelegramBot (Stock exchange app) - provides GUI to sell, buy stocks, subscribe for a stock change etc.

-Queue app- provides an effecient way to store messages
 consists of: 
    --Queue manager - manages the brokers (nodes), provides a admin panel to create topics, partitions etc.
    --Queue brokers - provide distributed storage of messages in files
    --Consumer(s) - provide distributed rading of messages from brokers
 
-MS SQL DataBase - provides storage of users queue's offsets, subscriptions, purchase and sale information


**Still in progress**
