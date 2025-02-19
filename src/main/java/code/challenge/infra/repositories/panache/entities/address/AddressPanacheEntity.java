package code.challenge.infra.repositories.panache.entities.address;

import code.challenge.app.domain.address.Address;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "address")
public class AddressPanacheEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public String street;
    public String number;
    public String complement;
    public String neighborhood;
    public String city;
    public String state;
    public String country;
    public String zipCode;

    public Address toDomainObject() {
        return new Address(id, street, number, complement, neighborhood, city, state, country, zipCode);
    }

    public static AddressPanacheEntity fromDomain(Address address) {
        var addressPanacheEntity = new AddressPanacheEntity();
        addressPanacheEntity.id = address.getId();
        addressPanacheEntity.street = address.getStreet();
        addressPanacheEntity.number = address.getNumber();
        addressPanacheEntity.complement = address.getComplement();
        addressPanacheEntity.neighborhood = address.getNeighborhood();
        addressPanacheEntity.city = address.getCity();
        addressPanacheEntity.state = address.getState();
        addressPanacheEntity.country = address.getCountry();
        addressPanacheEntity.zipCode = address.getZipCode();
        return addressPanacheEntity;
    }
}
