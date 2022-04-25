package queuebroker0.service;

public interface DataProcessor<R, I> {
    R process(I inputData);
}
