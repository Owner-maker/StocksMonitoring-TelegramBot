package queueapplication.exception;

public class ConsumerNotFoundException extends Exception{
    private final String userId;

    public ConsumerNotFoundException(String message, String userId){
        super(message);
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }
}
