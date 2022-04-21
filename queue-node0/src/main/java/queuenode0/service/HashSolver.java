package queuenode0.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class HashSolver {
    public int getIndex(String key, int partitionQuantity){
        return Math.abs(key.hashCode()%partitionQuantity);
    }
}
