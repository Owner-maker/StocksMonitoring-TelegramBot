package queueapplication.service.broker;

public interface DataInput<T,E> {
    T getData(E inputData);
}
