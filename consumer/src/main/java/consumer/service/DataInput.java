package consumer.service;

public interface DataInput<D, S> {
    D getData(S source);
}
