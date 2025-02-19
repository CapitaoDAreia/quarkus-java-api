package code.challenge.app.usecases.account;

import code.challenge.app.domain.account.Account;
import code.challenge.app.domain.customer.Customer;
import code.challenge.app.exceptions.account.AccountNotFoundException;
import code.challenge.app.logging.Logger;
import code.challenge.app.repositories.account.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InactivateAccountUseCaseTest {
    private final AccountRepository accountRepositoryMock = Mockito.mock(AccountRepository.class);
    private final Logger logMock = Mockito.mock(Logger.class);
    private final InactivateAccountUseCase useCase = new InactivateAccountUseCase(accountRepositoryMock, logMock);

    private final InactivateAccountUseCase.Input input = new InactivateAccountUseCase.Input("98765");

    @Test
    void shouldInactivateAccountSuccessfully() {
        var customer = new Customer();
        var account = Account.newCustomerAccount("12345", "0001", customer);

        when(accountRepositoryMock.getAccountByAccountNumber(input.accountNumber())).thenReturn(account);
        when(accountRepositoryMock.save(account)).thenReturn(account);

        var output = useCase.execute(input);

        assertEquals(false, output.account().getIsActive());

        verify(accountRepositoryMock).getAccountByAccountNumber(input.accountNumber());
        verify(accountRepositoryMock).save(account);
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound() {
        when(accountRepositoryMock.getAccountByAccountNumber(input.accountNumber())).thenReturn(null);

        assertThrows(AccountNotFoundException.class, () -> useCase.execute(input));
    }
}