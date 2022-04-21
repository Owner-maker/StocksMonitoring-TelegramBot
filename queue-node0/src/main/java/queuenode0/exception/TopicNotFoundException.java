package queuenode0.exception;

public class TopicNotFoundException extends Exception{
    private final String name;

    public TopicNotFoundException(String message, String name){
        super(message);
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
