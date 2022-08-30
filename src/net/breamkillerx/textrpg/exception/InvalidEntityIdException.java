package net.breamkillerx.textrpg.exception;

public class InvalidEntityIdException extends IllegalArgumentException {
    public InvalidEntityIdException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public InvalidEntityIdException(String errorMessage) {
        super(errorMessage);
    }
    public InvalidEntityIdException() {
        super();
    }
}
