package queueapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import queueapplication.config.QueueAddingMessagesState;
import queueapplication.exception.ConsumerNotFoundException;
import queueapplication.exception.TopicNotFoundException;
import queueapplication.handler.batch.StateKeyDefined;
import queueapplication.pojo.Broker;
import queueapplication.pojo.Consumer;
import queueapplication.pojo.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope("singleton")
public class Queue {

    private List<Broker> brokers;

    public static final int BATCH_SIZE = 10;
    public QueueAddingMessagesState state = QueueAddingMessagesState.KEY_DEFINED;

    private static final StateKeyDefined stateKeyDefined = new StateKeyDefined();

    private final HashMap<String, ArrayList<Message>> messagesCache = new HashMap<>();
    private final HashMap<String,Consumer> consumerCache = new HashMap<>();

    public void addMessageToCache(Message message) {
        messagesCache.get(message.getTopicName()).add(new Message(message));

        switch (state){
            case KEY_DEFINED:
                break;
            case ROUND_ROBIN:
                break;
            case EXPLICIT_PARTITION:
                stateKeyDefined.storeBatchOfMessages();
                break;
            default:
                stateKeyDefined.storeBatchOfMessages();
        }
    }

    public void addConsumer(Consumer consumer){
        consumerCache.put(consumer.getId(),consumer);

    }

    public Consumer getConsumer(String id){
        return consumerCache.get(id);
    }

    public Message getMessageFromCache(String userId, String topicName) throws ConsumerNotFoundException, TopicNotFoundException {
        Consumer consumer = consumerCache.get(userId);
        if(consumer==null){
            throw new ConsumerNotFoundException("No such consumer was found",userId);
        }
        if(!messagesCache.containsKey(topicName)){
            throw new TopicNotFoundException("No such topic was found",topicName);
        }

        int offset = consumer.getOffsetByTopic(topicName);
        var message = messagesCache.get(userId).get(offset);

        consumer.updateOffsetInTopic(topicName,++offset);
        return message;
    }

    public Map<String, ArrayList<Message>> getMessagesCache() {
        return messagesCache;
    }

    public void setStateToQueue(QueueAddingMessagesState state){
        this.state = state;
    }

    public boolean isCacheDataAbleToStoreInFileSystem() {
        var amountOfMessagesOfAllTopics = 0;
        for (ArrayList<Message> messages : messagesCache.values()) {
            amountOfMessagesOfAllTopics += messages.size();
        }
        return amountOfMessagesOfAllTopics >= BATCH_SIZE;
    }

    public void cleanCache() {
        messagesCache.clear();
    }
}
