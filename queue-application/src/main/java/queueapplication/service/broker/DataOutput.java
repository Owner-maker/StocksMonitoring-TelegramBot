package queueapplication.service.broker;

public interface DataOutput<R, S, D> {
    R create(S destination, D data);
}
