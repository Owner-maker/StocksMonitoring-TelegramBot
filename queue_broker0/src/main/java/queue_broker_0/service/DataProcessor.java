package queue_broker_0.service;

public interface DataProcessor<R, I> {
    R process(I inputData);
}
