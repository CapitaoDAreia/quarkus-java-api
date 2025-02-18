package code.challenge.app.exceptions.account;

public class ErrorCreatingAccountException extends RuntimeException {
    public ErrorCreatingAccountException(String message) {
        super(message);
    }
}
