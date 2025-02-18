package code.challenge.app.domain.customer;

import code.challenge.app.domain.address.Address;

import java.util.UUID;

public class Customer {
    private UUID id;
    private String name;
    private String surName;
    private String email;
    private String cpf;
    private Address address;

    public Customer() {
    }

    public Customer(UUID id, String name, String surName, String email, String cpf, Address address) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.cpf = cpf;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
