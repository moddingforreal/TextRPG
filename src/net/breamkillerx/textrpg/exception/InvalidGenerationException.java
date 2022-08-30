package net.breamkillerx.textrpg.exception;

public class InvalidGenerationException extends RuntimeException {
    public InvalidGenerationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public InvalidGenerationException(String errorMessage) {
        super(errorMessage);
    }
    public InvalidGenerationException() {
        super();
    }
}
