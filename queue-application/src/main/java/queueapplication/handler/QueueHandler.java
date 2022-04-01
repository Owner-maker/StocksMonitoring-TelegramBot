package queueapplication.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import queueapplication.config.QueueAddingMessagesState;
import queueapplication.handler.batch.StateBatch;
import queueapplication.service.Queue;

import java.nio.file.Paths;

@Component
@Scope("singleton")
public class QueueHandler {
    public static final String CURRENT_USER_LOGS_DIRECTORY = String.format("%s%s", System.getProperty("user.dir"), "\\src\\main\\resources\\static\\logs");

    private QueueHandler() {
    }

    @Autowired
    private Queue queue;

    public void storeBatchOfMessagesInFileSystem(StateBatch sender){
        if(queue.isCacheDataAbleToStoreInFileSystem()){
            sender.storeBatchOfMessages();
        }
    }
}
