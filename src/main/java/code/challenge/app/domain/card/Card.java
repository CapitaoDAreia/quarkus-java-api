package code.challenge.app.domain.card;

import code.challenge.app.domain.account.Account;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Card {
    private UUID id;
    private String number;
    private Date expirationDate;
    private String cvv;
    private Boolean isActive;
    private Boolean isBlocked;
    private String country;
    private UUID accountId;

    public Card() {
    }

    public Card(UUID id, String number, Date expirationDate, String cvv, Boolean isActive, Boolean isBlocked, String country) {
        this.id = id;
        this.number = number;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.isActive = isActive;
        this.isBlocked = isBlocked;
        this.country = country;
    }

    public Card(UUID id, String number, Date expirationDate, String cvv, Boolean isActive, Boolean isBlocked, String country, UUID accountId) {
        this.id = id;
        this.number = number;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.isActive = isActive;
        this.isBlocked = isBlocked;
        this.country = country;
        this.accountId = accountId;
    }

    public static Card newCard(String number, Date expirationDate, String cvv, String country, UUID accountId) {
        if (Objects.isNull(number)) {
            throw new IllegalArgumentException("number cannot be null");
        }

        if (Objects.isNull(expirationDate)) {
            throw new IllegalArgumentException("expiration date cannot be null");
        }

        if (Objects.isNull(cvv)) {
            throw new IllegalArgumentException("cvv cannot be null");
        }

        if (Objects.isNull(country)) {
            throw new IllegalArgumentException("country cannot be null");
        }

        if (Objects.isNull(accountId)) {
            throw new IllegalArgumentException("accountId cannot be null");
        }

        var newId = UUID.randomUUID();

        return new Card(newId, number, expirationDate, cvv, false, false, country, accountId);
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

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccount(UUID accountId) {
        this.accountId = accountId;
    }

    public void setExpirationDate(Date expirationDate) {
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
