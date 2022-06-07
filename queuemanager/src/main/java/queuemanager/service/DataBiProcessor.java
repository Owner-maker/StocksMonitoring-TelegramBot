package queuemanager.service;

public interface DataBiProcessor<R, I, U> {
    R process(I i, U u);
}