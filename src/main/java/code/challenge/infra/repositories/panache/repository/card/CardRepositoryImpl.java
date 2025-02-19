package code.challenge.infra.repositories.panache.repository.card;

import code.challenge.app.domain.card.Card;
import code.challenge.app.repositories.card.CardRepository;
import code.challenge.infra.repositories.panache.entities.card.CardPanacheEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Objects;

@ApplicationScoped
public class CardRepositoryImpl implements CardRepository {
    @Override
    public Card save(Card card) {
        CardPanacheEntity entity = CardPanacheEntity.fromDomain(card);
        entity.persistAndFlush();
        return entity.isPersistent() ? entity.toDomainObject() : null;
    }

    @Override
    public Boolean cardExists(String cardNumber) {
        return CardPanacheEntity.count(
                "cardNumber", cardNumber
        ) > 0;
    }

    @Override
    public Card getCardByCardNumber(String cardNumber) {
        CardPanacheEntity entity = CardPanacheEntity.find(
                "cardNumber", cardNumber
        ).firstResult();

        return Objects.nonNull(entity) ? entity.toDomainObject() : null;
    }
}
