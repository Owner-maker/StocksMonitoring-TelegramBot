package queueapplication.exception;

import java.io.IOException;

public class FileWriteException extends IOException {
    private final String fileName;

    public FileWriteException(String message, String fileName){
        super(message);
        this.fileName = fileName;
    }

    public String getFileName(){
        return fileName;
    }
}
