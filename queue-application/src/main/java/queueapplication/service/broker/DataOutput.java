package queueapplication.service.broker;

public interface DataOutput<T> {
    void write(T data);
}
