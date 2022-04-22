package queue_broker_0.handler;

public interface DataOutput<R, S, D> {
    R create(S destination, D data);
}
