package queueapplication.handler;

import queueapplication.pojo.Message;
import queueapplication.pojo.Topic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogFileHandler {
    public static int offsetCount = 0;

    public static void storeMessageInTopic(Message message){
        File logFileToStoreMessage = new File(String.format("%s\\%s\\%s",TopicHandler.CURRENT_USER_TOPICS_DIRECTORY, message.getTopicName(),TopicHandler.BASIC_LOG_FILE_NAME_OF_TOPIC));
        System.out.println(logFileToStoreMessage);
        try(FileWriter writer = new FileWriter(logFileToStoreMessage,true)){
            message.setOffset(offsetCount++);
            writer.write(message.toString());
            writer.append('\n');
        }
        catch (IOException e) {
            System.err.println(String.format("Message {%s} was not added to the queue",message.toString()));
        }
    }
}
