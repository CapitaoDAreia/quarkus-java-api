package code.challenge.infra.repositories.panache.entities.customer;

import code.challenge.app.domain.customer.Customer;
import code.challenge.infra.repositories.panache.entities.address.AddressPanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "customer")
public class CustomerPanacheEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public String name;
    public String surName;
    public String email;
    public String cpf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    public AddressPanacheEntity address;

    public CustomerPanacheEntity() {
    }

    public Customer toDomainObject() {
        var addressEntity = address.toDomainObject();
        return new Customer(id, name, surName, email, cpf, addressEntity);
    }

    public static CustomerPanacheEntity fromDomain(Customer customer) {
        var customerPanacheEntity = new CustomerPanacheEntity();
        customerPanacheEntity.id = customer.getId();
        customerPanacheEntity.name = customer.getName();
        customerPanacheEntity.surName = customer.getSurName();
        customerPanacheEntity.email = customer.getEmail();
        customerPanacheEntity.cpf = customer.getCpf();
        customerPanacheEntity.address = AddressPanacheEntity.fromDomain(customer.getAddress());
        return customerPanacheEntity;
    }
}
