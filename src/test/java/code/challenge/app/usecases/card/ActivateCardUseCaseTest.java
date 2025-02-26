package code.challenge.app.usecases.card;

import code.challenge.app.domain.account.Account;
import code.challenge.app.domain.card.Card;
import code.challenge.app.exceptions.card.CardNotFoundException;
import code.challenge.app.logging.Logger;
import code.challenge.app.repositories.card.CardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActivateCardUseCaseTest {

    private final CardRepository cardRepositoryMock = Mockito.mock(CardRepository.class);
    private final Logger logMock = Mockito.mock(Logger.class);

    private final ActivateCardUseCase useCase = new ActivateCardUseCase(
            cardRepositoryMock,
            logMock
    );

    private final ActivateCardUseCase.Input input = new ActivateCardUseCase.Input("1234567812345678");

    @Test
    void shouldActivateCardSuccessfully() {
        var card = new Card();
        card.setActive(false);

        when(cardRepositoryMock.getCardByCardNumber(input.cardNumber())).thenReturn(card);
        doNothing().when(cardRepositoryMock).update(any(Card.class));

        var output = useCase.execute(input);

        assertTrue(output.card().getActive(), "The card should be activated");

        verify(cardRepositoryMock).update(card);
        verify(logMock).info("Activating card with number: " + input.cardNumber());
    }

    @Test
    void shouldThrowExceptionWhenCardNotFound() {
        when(cardRepositoryMock.getCardByCardNumber(input.cardNumber())).thenReturn(null);

        assertThrows(CardNotFoundException.class, () -> useCase.execute(input));
    }

    @Test
    void shouldActivateCardAndSetActiveTrue() {
        var card = new Card();
        var account = new Account();
        var expectedCard = new Card();

        card.setActive(false);
        expectedCard.setActive(true);

        when(cardRepositoryMock.getCardByCardNumber(input.cardNumber())).thenReturn(card);
        when(cardRepositoryMock.save(card, account)).thenReturn(expectedCard);

        var output = useCase.execute(input);

        assertTrue(output.card().getActive());
    }
}