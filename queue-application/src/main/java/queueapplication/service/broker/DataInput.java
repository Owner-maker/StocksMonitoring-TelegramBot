package queueapplication.service.broker;

public interface DataInput<D, S> {
    D getData(S source);
}
