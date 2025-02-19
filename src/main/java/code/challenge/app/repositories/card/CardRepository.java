package code.challenge.app.repositories.card;

import code.challenge.app.domain.account.Account;
import code.challenge.app.domain.card.Card;

public interface CardRepository {
    Card save(Card card, Account account);
    void update(Card card);
    Boolean cardExists(String cardNumber);
    Card getCardByCardNumber(String cardNumber);
}
