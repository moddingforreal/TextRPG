package net.breamkillerx.textrpg.exception;

public class IllegalCombatStateException extends IllegalStateException {
    public IllegalCombatStateException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public IllegalCombatStateException(String errorMessage) {
        super(errorMessage);
    }
    public IllegalCombatStateException() {
        super();
    }
}
