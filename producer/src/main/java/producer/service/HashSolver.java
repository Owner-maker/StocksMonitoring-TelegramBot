package producer.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HashSolver {
    public Optional<Integer> getIndex(String key, int partitionQuantity){
        Integer result = null;
        if(partitionQuantity!=0){
            result = Math.abs(key.hashCode() % partitionQuantity);
        }
        return Optional.ofNullable(result);
    }
}
