package code.challenge.app.usecases.card;

import code.challenge.app.domain.card.Card;
import code.challenge.app.exceptions.card.CardAlreadyExistsException;
import code.challenge.app.exceptions.card.CreateCardException;
import code.challenge.app.logging.Logger;
import code.challenge.app.repositories.account.AccountRepository;
import code.challenge.app.repositories.card.CardRepository;
import code.challenge.app.usecases.UseCase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Date;
import java.util.Optional;

@ApplicationScoped
public class CreateCardUseCase extends UseCase<CreateCardUseCase.Input, CreateCardUseCase.Output> {
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;
    private final Logger log;

    public CreateCardUseCase(
            CardRepository cardRepository,
            AccountRepository accountRepository,
            Logger log
    ) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
        this.log = log;
    }

    @Override
    public Output execute(Input input) {
        log.info("Creating card to account number " + input.accountNumber());

        var cardAlreadyExists = Optional
                .ofNullable(cardRepository.cardExists(input.cardNumber()))
                .orElseThrow(() -> {
                    var msg = "Error checking if card already exists";
                    log.error(msg);
                    return new CreateCardException(msg);
                });

        if (cardAlreadyExists) {
            log.error("Card already exists");
            throw new CardAlreadyExistsException();
        }

        var account = Optional
                .ofNullable(accountRepository.getAccountByAccountNumber(input.accountNumber()))
                .orElseThrow(() -> {
                    var message = "Error retrieving account";
                    log.error(message);
                    return new CreateCardException(message);
                });

        var card = Card.newCard(input.cardNumber(), input.expirationDate(), input.cvv(), input.country(), account);

        var createdAccount = cardRepository.save(card);

        log.info("Card created successfully");

        return new Output(createdAccount);
    }

    public record Input(String cardNumber, Date expirationDate, String cvv, String country, String accountNumber) {
    }

    public record Output(Card card) {
    }
}
