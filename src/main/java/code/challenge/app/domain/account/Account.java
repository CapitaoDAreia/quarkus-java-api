package code.challenge.app.domain.account;

import code.challenge.app.domain.card.Card;
import code.challenge.app.domain.customer.Customer;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Account {
    private UUID id;
    private String accountNumber;
    private String agency;
    private Customer customer;
    private List<Card> cards;
    private Boolean isActive;

    public Account() {
    }

    public Account(UUID id, String accountNumber, String agency, Customer customer, List<Card> cards, Boolean isActive) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("customer id cannot be null");
        }

        this.id = id;
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.agency = agency;
        this.cards = cards;
        this.isActive = isActive;
    }

    public static Account newCustomerAccount(
            String accountNumber,
            String agency,
            Customer customer
    ) {
        if (Objects.isNull(customer)) {
            throw new IllegalArgumentException("customer cannot be null");
        }

        if (Objects.isNull(accountNumber)) {
            throw new IllegalArgumentException("account number cannot be null");
        }

        if (Objects.isNull(agency)) {
            throw new IllegalArgumentException("agency cannot be null");
        }

        var newId = UUID.randomUUID();

        return new Account(newId, accountNumber, agency, customer, null, true);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
