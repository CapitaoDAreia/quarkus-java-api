package code.challenge.app.exceptions.card;

public class CardAlreadyExistsException extends RuntimeException {
    public CardAlreadyExistsException() {
        super("Card already exists");
    }
}
