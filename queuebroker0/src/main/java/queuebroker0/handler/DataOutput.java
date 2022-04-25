package queuebroker0.handler;

public interface DataOutput<R, D> {
    R create(D data);
}
