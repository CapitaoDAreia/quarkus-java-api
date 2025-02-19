package code.challenge.infra.repositories.panache.entities.card;

import code.challenge.app.domain.card.Card;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "card")
public class CardPanacheEntity extends PanacheEntityBase {
    @Id
    public UUID id;
    public String number;
    public Date expirationDate;
    public String cvv;
    public Boolean isActive;
    public Boolean isBlocked;
    public String country;

    public Card toDomainObject() {
        return new Card(id, number, expirationDate, cvv, isActive, isBlocked, country);
    }

    public static CardPanacheEntity fromDomain(Card card) {
        var cardPanacheEntity = new CardPanacheEntity();
        cardPanacheEntity.id = card.getId();
        cardPanacheEntity.number = card.getNumber();
        cardPanacheEntity.expirationDate = card.getExpirationDate();
        cardPanacheEntity.cvv = card.getCvv();
        cardPanacheEntity.isActive = card.getActive();
        cardPanacheEntity.isBlocked = card.getBlocked();
        cardPanacheEntity.country = card.getCountry();
        return cardPanacheEntity;
    }
}
