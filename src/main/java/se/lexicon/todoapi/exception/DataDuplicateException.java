package se.lexicon.todoapi.exception;

public class DataDuplicateException extends Exception {

    public DataDuplicateException(String message) {
        super(message);
    }

    public DataDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
