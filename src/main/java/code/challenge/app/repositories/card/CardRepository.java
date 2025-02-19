package code.challenge.app.repositories.card;

import code.challenge.app.domain.card.Card;

public interface CardRepository {
    Card save(Card card);
    void update(Card card);
    Boolean cardExists(String cardNumber);
    Card getCardByCardNumber(String cardNumber);
}
