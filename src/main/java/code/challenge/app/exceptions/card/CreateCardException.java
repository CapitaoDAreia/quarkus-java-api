package code.challenge.app.exceptions.card;

public class CreateCardException extends RuntimeException {
    public CreateCardException(
            String message
    ) {
        super(message);
    }
}
