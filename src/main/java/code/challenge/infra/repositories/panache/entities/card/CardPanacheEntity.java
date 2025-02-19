package code.challenge.infra.repositories.panache.entities.card;

import code.challenge.app.domain.card.Card;
import code.challenge.infra.repositories.panache.entities.account.AccountPanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "card")
public class CardPanacheEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public String number;
    public String expirationDate;
    public String cvv;
    public Boolean isActive;
    public Boolean isBlocked;
    public String country;

    @ManyToOne
    @JoinColumn(name = "account_id")
    public AccountPanacheEntity account;

    public Card toDomainObject() {
        var domainAccount = account.toDomainObject();
        return new Card(id, number, expirationDate, cvv, isActive, isBlocked, country, domainAccount);
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
        cardPanacheEntity.account = AccountPanacheEntity.fromDomain(card.getAccount());
        return cardPanacheEntity;
    }
}
