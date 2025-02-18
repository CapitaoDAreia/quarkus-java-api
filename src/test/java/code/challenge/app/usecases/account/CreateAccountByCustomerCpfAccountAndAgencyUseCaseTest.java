package code.challenge.app.usecases.account;

import code.challenge.app.domain.account.Account;
import code.challenge.app.domain.customer.Customer;
import code.challenge.app.exceptions.account.ErrorCreatingAccountException;
import code.challenge.app.logging.Logger;
import code.challenge.app.repositories.account.AccountRepository;
import code.challenge.app.repositories.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateAccountByCustomerCpfAccountAndAgencyUseCaseTest {
    private final AccountRepository accountRepositoryMock = Mockito.mock(AccountRepository.class);
    private final CustomerRepository customerRepositoryMock = Mockito.mock(CustomerRepository.class);
    private final Logger logMock = Mockito.mock(Logger.class);

    private final CreateAccountByCustomerCpfAccountAndAgencyUseCase useCase = new CreateAccountByCustomerCpfAccountAndAgencyUseCase(
            accountRepositoryMock,
            customerRepositoryMock,
            logMock
    );

    private final CreateAccountByCustomerCpfAccountAndAgencyUseCase.Input input = new CreateAccountByCustomerCpfAccountAndAgencyUseCase
            .Input("12345678900", "1001", "0001");

    @Test
    void shouldCreateAccountSuccessfully() {
        var existentCustomer = new Customer();
        var expectedAccount = Account.newCustomerAccount(input.accountNumber(), input.agency(), existentCustomer);

        when(customerRepositoryMock.getCustomerByCpf(input.customerCpf())).thenReturn(existentCustomer);
        when(accountRepositoryMock.save(any(Account.class))).thenReturn(expectedAccount);

        var output = useCase.execute(input);

        assertEquals(expectedAccount, output.account());

        verify(accountRepositoryMock).save(any(Account.class));
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        when(customerRepositoryMock.getCustomerByCpf(input.customerCpf())).thenReturn(null);

        assertThrows(ErrorCreatingAccountException.class, () -> useCase.execute(input));
    }

    @Test
    void shouldThrowExceptionWhenAccountAlreadyExists() {
        var existentCustomer = new Customer();

        when(customerRepositoryMock.getCustomerByCpf(input.customerCpf())).thenReturn(existentCustomer);
        when(accountRepositoryMock.accountExists(input.accountNumber(), input.agency())).thenReturn(true);

        assertThrows(ErrorCreatingAccountException.class, () -> useCase.execute(input));

        verify(accountRepositoryMock).accountExists(input.accountNumber(), input.agency());
    }

    @Test
    void shouldThrowExceptionWhenNotPossibleToVerifyAccountExistence() {
        var existentCustomer = new Customer();

        when(customerRepositoryMock.getCustomerByCpf(input.customerCpf())).thenReturn(existentCustomer);
        when(accountRepositoryMock.accountExists(input.accountNumber(), input.agency())).thenReturn(null);

        assertThrows(ErrorCreatingAccountException.class, () -> useCase.execute(input));

        verify(accountRepositoryMock).accountExists(input.accountNumber(), input.agency());
    }
}
