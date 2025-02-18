package code.challenge.app.repositories.account;

import code.challenge.app.domain.account.Account;

public interface AccountRepository {
    Account getAccountByCpf(String cpf);
    Account save(Account account);
    Account getAccountByAccountNumber(String accountNumber);
}
