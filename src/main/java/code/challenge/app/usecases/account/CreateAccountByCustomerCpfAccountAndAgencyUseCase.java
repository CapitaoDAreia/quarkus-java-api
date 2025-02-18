package code.challenge.app.usecases.account;

import code.challenge.app.domain.account.Account;
import code.challenge.app.exceptions.account.ErrorCreatingAccountException;
import code.challenge.app.logging.Logger;
import code.challenge.app.repositories.account.AccountRepository;
import code.challenge.app.repositories.customer.CustomerRepository;
import code.challenge.app.usecases.UseCase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class CreateAccountByCustomerCpfAccountAndAgencyUseCase extends UseCase<CreateAccountByCustomerCpfAccountAndAgencyUseCase.Input, CreateAccountByCustomerCpfAccountAndAgencyUseCase.Output> {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final Logger log;

    public CreateAccountByCustomerCpfAccountAndAgencyUseCase(
            AccountRepository accountRepository,
            CustomerRepository customerRepository,
            Logger log
    ) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.log = log;
    }

    @Override
    public Output execute(Input input) {
        log.info("Creating account for customer id: " + input.customerCpf());

        log.info("Fetching customer by cpf");

        var customer = Optional
                .ofNullable(customerRepository.getCustomerByCpf(input.customerCpf()))
                .orElseThrow(() -> {
                    var msg = "Customer not found";
                    log.error(msg);
                    return new ErrorCreatingAccountException(msg);
                });

        var accountAlreadyExists = Optional
                .ofNullable(accountRepository.accountExists(input.accountNumber(), input.agency()))
                .orElseThrow(() -> {
                    var msg = "Error checking if account already exists";
                    log.warn(msg);
                    return new ErrorCreatingAccountException(msg);
                });

        if (accountAlreadyExists) {
            var msg = "Account already exists";
            log.warn(msg);
            throw new ErrorCreatingAccountException(msg);
        }

        var account = Account.newCustomerAccount(
                input.accountNumber(),
                input.agency(),
                customer
        );

        var createdAccount = accountRepository.save(account);

        return new Output(createdAccount);
    }

    public record Input(String customerCpf, String accountNumber, String agency) {
    }

    public record Output(Account account) {
    }
}
