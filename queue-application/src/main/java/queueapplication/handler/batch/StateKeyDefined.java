package queueapplication.handler.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import queueapplication.pojo.Message;

import java.util.ArrayList;

@Component
public class StateKeyDefined implements StateBatch{

    @Autowired
    private Queue queue;

    @Override
    public void storeBatchOfMessages() {
        for(ArrayList<Message> messages: queue.getMessagesCache().values()){
            for(Message message: messages){

            }
        }
    }
}
