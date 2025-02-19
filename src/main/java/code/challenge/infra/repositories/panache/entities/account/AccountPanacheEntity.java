package code.challenge.infra.repositories.panache.entities.account;

import code.challenge.app.domain.account.Account;
import code.challenge.infra.repositories.panache.entities.card.CardPanacheEntity;
import code.challenge.infra.repositories.panache.entities.customer.CustomerPanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Objects;

import java.util.UUID;

@Entity
@Table(name = "account")
public class AccountPanacheEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public String accountNumber;
    public String agency;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    public CustomerPanacheEntity customer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id")
    public List<CardPanacheEntity> cards;

    public Boolean isActive;

    public Account toDomainObject() {
        var domainCards = this.cards.stream().map(CardPanacheEntity::toDomainObject).toList();
        var domainCustomer = Objects.nonNull(this.customer) ? this.customer.toDomainObject() : null;

        return new Account(id, accountNumber, agency, domainCustomer, domainCards, isActive);
    }

    public static AccountPanacheEntity fromDomain(Account account) {
        var accountPanacheEntity = new AccountPanacheEntity();
        accountPanacheEntity.id = account.getId();
        accountPanacheEntity.accountNumber = account.getAccountNumber();
        accountPanacheEntity.agency = account.getAgency();

        if (Objects.nonNull(account.getCustomer())) {
            accountPanacheEntity.customer = CustomerPanacheEntity.fromDomain(account.getCustomer());
        }

        if (Objects.nonNull(account.getCards())) {
            accountPanacheEntity.cards = account.getCards().stream()
                    .map(CardPanacheEntity::fromDomain)
                    .toList();
        }

        return accountPanacheEntity;
    }
}
