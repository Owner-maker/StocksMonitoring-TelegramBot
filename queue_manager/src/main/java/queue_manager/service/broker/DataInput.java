package queue_manager.service.broker;

public interface DataInput<D, S> {
    D getData(S source);
}
