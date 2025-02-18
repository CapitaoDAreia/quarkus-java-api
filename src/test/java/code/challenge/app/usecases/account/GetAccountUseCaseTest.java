package code.challenge.app.usecases.account;

import code.challenge.app.domain.account.Account;
import code.challenge.app.exceptions.account.AccountNotFoundException;
import code.challenge.app.logging.Logger;
import code.challenge.app.repositories.account.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetAccountUseCaseTest {
    private final AccountRepository accountRepositoryMock = Mockito.mock(AccountRepository.class);
    private final Logger logMock = Mockito.mock(Logger.class);
    private final GetAccountUseCase useCase = new GetAccountUseCase(accountRepositoryMock, logMock);

    private final GetAccountUseCase.Input input = new GetAccountUseCase.Input("12345678900");


    @Test
    void shouldFetchAccountSuccessfully() {
        var account = Account.newCustomerAccount("1001", "0001", null);
        when(accountRepositoryMock.getAccountByCpf(input.cpf())).thenReturn(account);

        var output = useCase.execute(input);

        assertEquals(account.getAccountNumber(), output.accountNumber());
        assertEquals(account.getAgency(), output.agency());

        verify(accountRepositoryMock).getAccountByCpf(input.cpf());
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound() {
        when(accountRepositoryMock.getAccountByCpf(input.cpf())).thenReturn(null);

        assertThrows(AccountNotFoundException.class, () -> useCase.execute(input));
    }
}