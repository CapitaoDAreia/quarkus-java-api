package code.challenge.app.usecases.card;

import code.challenge.app.domain.card.Card;
import code.challenge.app.exceptions.card.CardNotFoundException;
import code.challenge.app.logging.Logger;
import code.challenge.app.repositories.card.CardRepository;
import code.challenge.app.usecases.UseCase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ActivateCardUseCase extends UseCase<ActivateCardUseCase.Input, ActivateCardUseCase.Output> {

    private final CardRepository cardRepository;
    private final Logger log;

    public ActivateCardUseCase(
            CardRepository cardRepository,
            Logger log
    ) {
        this.cardRepository = cardRepository;
        this.log = log;
    }

    @Override
    public Output execute(Input input) {
        log.info("Activating card with number: " + input.cardNumber());

        var card = Optional
                .ofNullable(cardRepository.getCardByCardNumber(input.cardNumber()))
                .orElseThrow(() -> {
                    log.error("Card not found while trying to activate");
                    return new CardNotFoundException();
                });

        card.setActive(true);

        cardRepository.update(card);

        return new Output(card);
    }

    public record Input(String cardNumber) {
    }

    public record Output(Card card) {
    }
}
