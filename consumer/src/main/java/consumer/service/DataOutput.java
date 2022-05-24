package consumer.service;

public interface DataOutput<R, S, D> {
    R create(S destination, D data);
}
