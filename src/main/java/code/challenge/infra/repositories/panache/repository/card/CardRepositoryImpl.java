package code.challenge.infra.repositories.panache.repository.card;

import code.challenge.app.domain.account.Account;
import code.challenge.app.domain.card.Card;
import code.challenge.app.repositories.card.CardRepository;
import code.challenge.infra.repositories.panache.entities.account.AccountPanacheEntity;
import code.challenge.infra.repositories.panache.entities.card.CardPanacheEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Objects;

@ApplicationScoped
public class CardRepositoryImpl implements CardRepository {
    @Override
    public Card save(Card card, Account account) {
        AccountPanacheEntity accountPanacheEntity = AccountPanacheEntity.findById(account.getId());

        if (Objects.isNull(accountPanacheEntity)) {
            throw new IllegalArgumentException("Account not found with ID: " + account.getId());
        }

        CardPanacheEntity entity = CardPanacheEntity.fromDomain(card, accountPanacheEntity);
        entity.persistAndFlush();
        return entity.isPersistent() ? entity.toDomainObject(account) : null;
    }

    @Override
    public void update(Card card) {
        CardPanacheEntity.update("isActive = true where number = ?1", card.getNumber());
    }

    @Override
    public Boolean cardExists(String cardNumber) {
        return CardPanacheEntity.count(
                "number", cardNumber
        ) > 0;
    }

    @Override
    public Card getCardByCardNumber(String cardNumber) {
        CardPanacheEntity entity = CardPanacheEntity.find(
                "number", cardNumber
        ).firstResult();

        return Objects.nonNull(entity) ? entity.toDomainObject() : null;
    }
}
