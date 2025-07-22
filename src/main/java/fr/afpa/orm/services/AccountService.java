package fr.afpa.orm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.afpa.orm.entities.Account;
import fr.afpa.orm.repositories.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAll() {
        return (List<Account>) accountRepository.findAll();
    }

    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public void delete(Account account) {
        accountRepository.delete(account);
    }
}
