package queuebroker0.service;

import org.springframework.stereotype.Component;

@Component
public class HashSolver {
    public int getIndex(String key, int partitionQuantity){
        return Math.abs(key.hashCode()%partitionQuantity);
    }
}
