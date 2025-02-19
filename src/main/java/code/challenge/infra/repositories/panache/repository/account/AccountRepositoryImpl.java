package code.challenge.infra.repositories.panache.repository.account;

import code.challenge.app.domain.account.Account;
import code.challenge.app.repositories.account.AccountRepository;
import code.challenge.infra.repositories.panache.entities.account.AccountPanacheEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Objects;

@ApplicationScoped
public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public Account getAccountByCpf(String cpf) {
        AccountPanacheEntity entity = AccountPanacheEntity.find(
                "customer.cpf", cpf
        ).firstResult();

        return Objects.nonNull(entity) ? entity.toDomainObject() : null;
    }

    @Override
    public Account save(Account account) {
        AccountPanacheEntity entity = AccountPanacheEntity.fromDomain(account);
        entity.persistAndFlush();
        return entity.isPersistent() ? entity.toDomainObject() : null;
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        AccountPanacheEntity entity = AccountPanacheEntity.find(
                "accountNumber", accountNumber
        ).firstResult();

        return Objects.nonNull(entity) ? entity.toDomainObject() : null;
    }

    @Override
    public Boolean accountExists(String accountNumber, String agency) {
        return AccountPanacheEntity.count(
                "accountNumber = ?1 and agency = ?2",
                accountNumber, agency
        ) > 0;
    }
}
