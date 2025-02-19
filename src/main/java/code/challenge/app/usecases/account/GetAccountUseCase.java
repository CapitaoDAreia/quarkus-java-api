package code.challenge.app.usecases.account;

import code.challenge.app.domain.card.Card;
import code.challenge.app.domain.customer.Customer;
import code.challenge.app.exceptions.account.AccountNotFoundException;
import code.challenge.app.logging.Logger;
import code.challenge.app.repositories.account.AccountRepository;
import code.challenge.app.usecases.UseCase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class GetAccountUseCase extends UseCase<GetAccountUseCase.Input, GetAccountUseCase.Output> {
    private final AccountRepository accountRepository;
    private final Logger log;

    public GetAccountUseCase(
            AccountRepository accountRepository,
            Logger log
    ) {
        this.accountRepository = accountRepository;
        this.log = log;
    }

    @Override
    public Output execute(Input input) {
        log.info("Fetching customer account");

        var account = Optional
                .ofNullable(accountRepository.getAccountByCpf(input.cpf()))
                .orElseThrow(() -> {
                    log.warn("User account not found");
                    return new AccountNotFoundException();
                });

        return new Output(
                account.getId(),
                account.getAccountNumber(),
                account.getAgency(),
                account.getCustomer(),
                account.getCards(),
                account.getIsActive()
        );
    }

    public record Input(String cpf) {
    }

    public record Output(UUID id, String accountNumber, String agency, Customer customer, List<Card> cards, Boolean isActive) {
    }
}
