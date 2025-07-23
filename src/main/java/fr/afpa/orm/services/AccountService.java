package fr.afpa.orm.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.afpa.orm.dto.AccountDto;
import fr.afpa.orm.entities.Account;
import fr.afpa.orm.mappers.AccountDtoMapper;
import fr.afpa.orm.repositories.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountDtoMapper accountDtoMapper;

    public AccountService(AccountRepository accountRepository, AccountDtoMapper accountDtoMapper) {
        this.accountRepository = accountRepository;
        this.accountDtoMapper = accountDtoMapper;
    }

    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(accountDtoMapper::apply)
                .collect(Collectors.toList());
    }

    public Optional<AccountDto> findById(Long id) {
        return accountRepository.findById(id)
                .map(accountDtoMapper::apply);
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> update(Long id, AccountDto accountDto) {
        return accountRepository.findById(id).map(existingAccount -> {
            existingAccount.setBalance(accountDto.getBalance());
            existingAccount.setCreationTime(accountDto.getCreationTime());
            return accountRepository.save(existingAccount);
        });
    }

    public void delete(Account account) {
        accountRepository.delete(account);
    }

    public boolean deleteById(Long id) {
        return accountRepository.findById(id)
                .map(account -> {
                    accountRepository.delete(account);
                    return true;
                }).orElse(false);
    }
}
