package fr.afpa.orm.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.orm.dto.AccountDto;
import fr.afpa.orm.entities.Account;
import fr.afpa.orm.mappers.AccountDtoMapper;
import fr.afpa.orm.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountDtoMapper accountMapper;

    // private final AccountRepository accountRepository;

    // public AccountRestController(AccountRepository accountRepository) {
    // this.accountRepository = accountRepository;
    // }

    public AccountRestController(AccountService accountService, AccountDtoMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    /**
     * Traite les requêtes GET et qui renvoie une liste de comptes
     */
    @GetMapping
    public List<AccountDto> getAll() {
        return accountService.findAll();
    }

    /**
     * Traite les requêtes GET avec un identifiant "variable de chemin" et qui
     * retourne les informations du compte associé
     */
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getOne(@PathVariable long id) {
        return accountService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Traite les requêtes POST pour créer un compte
     **/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto create(@RequestBody AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setCreationTime(accountDto.getCreationTime());
        account.setBalance(accountDto.getBalance());
        Account savedAccount = accountService.save(account);
        return accountMapper.apply(savedAccount);
    }

    /**
     * Traite les requêtes PUT
     */
    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> update(@PathVariable long id, @RequestBody AccountDto accountDto) {
        return accountService.update(id, accountDto)
                .map(updatedAccount -> ResponseEntity.ok(accountMapper.apply(updatedAccount)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Traite les requêtes DELETE pour supprimer un compte
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable long id) {
        if (accountService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
