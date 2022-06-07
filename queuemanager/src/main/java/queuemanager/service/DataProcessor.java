package queuemanager.service;

public interface DataProcessor<R, I> {
    R process(I inputData);
}
