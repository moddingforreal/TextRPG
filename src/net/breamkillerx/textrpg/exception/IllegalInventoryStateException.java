package net.breamkillerx.textrpg.exception;

public class IllegalInventoryStateException extends IllegalStateException {
    public IllegalInventoryStateException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public IllegalInventoryStateException(String errorMessage) {
        super(errorMessage);
    }
    public IllegalInventoryStateException() {
        super();
    }
}
