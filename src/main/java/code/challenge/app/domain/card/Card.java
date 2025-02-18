package code.challenge.app.domain.card;

import code.challenge.app.domain.account.Account;

import java.util.UUID;

public class Card {
    private UUID id;
    private String number;
    private String expirationDate;
    private String cvv;
    private Boolean isActive;
    private Boolean isBlocked;
    private String country;
    private Account account;

    public Card() {
    }

    public Card(UUID id, String number, String expirationDate, String cvv, Boolean isActive, Boolean isBlocked, String country, Account account) {
        this.id = id;
        this.number = number;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.isActive = isActive;
        this.isBlocked = isBlocked;
        this.country = country;
        this.account = account;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
