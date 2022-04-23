package producer.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class HashSolver {
    public int getIndex(String key, int partitionQuantity){
        return Math.abs(key.hashCode()%partitionQuantity);
    }
}
