package code.challenge.app.usecases.account;

import code.challenge.app.domain.account.Account;
import code.challenge.app.exceptions.account.AccountNotFoundException;
import code.challenge.app.logging.Logger;
import code.challenge.app.repositories.account.AccountRepository;
import code.challenge.app.usecases.UseCase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class InactivateAccountUseCase extends UseCase<InactivateAccountUseCase.Input, InactivateAccountUseCase.Output> {
    private final AccountRepository accountRepository;
    private final Logger log;

    public InactivateAccountUseCase(
            AccountRepository accountRepository,
            Logger log
    ) {
        this.accountRepository = accountRepository;
        this.log = log;
    }

    @Override
    public Output execute(Input input) {
        log.info("Inactivating account by account number");

        var account = Optional
                .ofNullable(accountRepository.getAccountByAccountNumber(input.accountNumber()))
                .orElseThrow(() -> {
                    log.warn("Account not found while inactivating account");
                    return new AccountNotFoundException();
                });

        account.setIsActive(false);
        accountRepository.update(account);

        log.info("Account inactivated successfully");

        return new Output(account);
    }

    public record Input(String accountNumber) {
    }

    public record Output(Account account) {
    }
}
