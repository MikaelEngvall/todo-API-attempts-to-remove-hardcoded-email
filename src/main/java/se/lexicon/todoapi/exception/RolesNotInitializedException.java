package se.lexicon.todoapi.exception;

public class RolesNotInitializedException extends RuntimeException {

    public RolesNotInitializedException(String message) {
        super(message);
    }

    public RolesNotInitializedException(String message, Throwable cause) {
        super(message, cause);
    }
}
