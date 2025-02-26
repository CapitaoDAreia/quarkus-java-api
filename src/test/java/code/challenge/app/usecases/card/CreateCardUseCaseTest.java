package code.challenge.app.usecases.card;

import code.challenge.app.domain.card.Card;
import code.challenge.app.domain.account.Account;
import code.challenge.app.exceptions.card.CardAlreadyExistsException;
import code.challenge.app.exceptions.card.CreateCardException;
import code.challenge.app.logging.Logger;
import code.challenge.app.repositories.account.AccountRepository;
import code.challenge.app.repositories.card.CardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCardUseCaseTest {
    private final CardRepository cardRepositoryMock = Mockito.mock(CardRepository.class);
    private final AccountRepository accountRepositoryMock = Mockito.mock(AccountRepository.class);
    private final Logger logMock = Mockito.mock(Logger.class);

    private final CreateCardUseCase useCase = new CreateCardUseCase(
            cardRepositoryMock,
            accountRepositoryMock,
            logMock
    );

    private final CreateCardUseCase.Input input = new CreateCardUseCase.Input("1234567812345678", new Date(), "123", "BR", "1001");

    @Test
    void shouldCreateCardSuccessfully() {
        var account = new Account();
        var accountId = UUID.randomUUID();
        account.setId(accountId);

        var expectedCard = Card.newCard(input.cardNumber(), input.expirationDate(), input.cvv(), input.country(), accountId);
        var expectedOutput = new CreateCardUseCase.Output();

        when(cardRepositoryMock.cardExists(input.cardNumber())).thenReturn(false);
        when(accountRepositoryMock.getAccountByAccountNumber(input.accountNumber())).thenReturn(account);
        when(cardRepositoryMock.save(any(Card.class), any(Account.class))).thenReturn(expectedCard);

        var output = useCase.execute(input);

        assertEquals(expectedOutput, output);

        verify(cardRepositoryMock).save(any(Card.class), any(Account.class));
        verify(logMock).info("Creating card to account number " + input.accountNumber());
        verify(logMock).info("Card created successfully");
    }

    @Test
    void shouldThrowExceptionWhenCardAlreadyExists() {
        when(cardRepositoryMock.cardExists(input.cardNumber())).thenReturn(true);

        assertThrows(CardAlreadyExistsException.class, () -> useCase.execute(input));
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound() {
        when(cardRepositoryMock.cardExists(input.cardNumber())).thenReturn(false);
        when(accountRepositoryMock.getAccountByAccountNumber(input.accountNumber())).thenReturn(null);

        assertThrows(CreateCardException.class, () -> useCase.execute(input));
    }

    @Test
    void shouldThrowExceptionWhenErrorCheckingCardExistence() {
        when(cardRepositoryMock.cardExists(input.cardNumber())).thenReturn(null);

        assertThrows(CreateCardException.class, () -> useCase.execute(input));
    }
}