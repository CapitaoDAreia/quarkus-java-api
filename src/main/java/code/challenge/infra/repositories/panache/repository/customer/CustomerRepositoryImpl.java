package code.challenge.infra.repositories.panache.repository.customer;

import code.challenge.app.domain.customer.Customer;
import code.challenge.app.repositories.customer.CustomerRepository;
import code.challenge.infra.repositories.panache.entities.customer.CustomerPanacheEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Objects;

@ApplicationScoped
public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public Customer getCustomerByCpf(String cpf) {
        CustomerPanacheEntity entity = CustomerPanacheEntity.find(
                "cpf", cpf
        ).firstResult();

        return Objects.nonNull(entity) ? entity.toDomainObject() : null;
    }
}
