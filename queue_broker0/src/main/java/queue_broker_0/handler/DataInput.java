package queue_broker_0.handler;

public interface DataInput<D, S, I> {
    D getData(S source, I inputData);
}
