package code.challenge.app.domain.account;

import code.challenge.app.domain.card.Card;
import code.challenge.app.domain.customer.Customer;

import java.util.List;
import java.util.UUID;

public class Account {
    private UUID id;
    private String accountNumber;
    private String agency;
    private Customer customer;
    private List<Card> cards;

    public Account() {
    }

    public Account(UUID id, String accountNumber, String agency, Customer customer, List<Card> cards) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.agency = agency;
        this.cards = cards;
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
}
