package queue_broker_0.handler;

public interface DataInput<D, I> {
    D getData(I inputData);
}
