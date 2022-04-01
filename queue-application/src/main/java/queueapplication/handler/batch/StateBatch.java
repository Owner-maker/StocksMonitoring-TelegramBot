package queueapplication.handler.batch;

import queueapplication.service.Queue;

public interface StateBatch {
    void storeBatchOfMessages();
}
