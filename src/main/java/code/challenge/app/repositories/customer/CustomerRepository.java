package code.challenge.app.repositories.customer;

import code.challenge.app.domain.customer.Customer;

public interface CustomerRepository {
    Customer getCustomerByCpf(String cpf);
}
