package fr.afpa.orm.web.controllers;

import java.util.List;
// import java.util.Objects;
import java.util.Optional;
// import java.util.stream.Collectors;
// import java.util.stream.Stream;
// import java.util.stream.StreamSupport;
// import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
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
// import fr.afpa.orm.dto.AccountDto;
import fr.afpa.orm.entities.Account;
// import fr.afpa.orm.repositories.AccountRepository;
import fr.afpa.orm.services.AccountService;
// import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    // private final AccountRepository accountRepository;

    // public AccountRestController(AccountRepository accountRepository) {
    // this.accountRepository = accountRepository;
    // }

    public AccountRestController(AccountService accountService, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    /**
     * Traite les requêtes GET et qui renvoie une liste de comptes
     */
    @GetMapping
    public List<AccountDto> getAll() {
        return accountService.findAll().stream()
                .map(account -> modelMapper.map(account, AccountDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Traite les requêtes GET avec un identifiant "variable de chemin" et qui
     * retourne les informations du compte associé
     */
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getOne(@PathVariable long id) {
        Optional<Account> account = accountService.findById(id);
        return account.map(a -> ResponseEntity.ok(modelMapper.map(a, AccountDto.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Traite les requêtes POST pour créer un compte
     **/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto create(@RequestBody AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        Account savedAccount = accountService.save(account);
        return modelMapper.map(savedAccount, AccountDto.class);
    }

    /**
     * Traite les requêtes PUT
     */
    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> update(@PathVariable long id, @RequestBody AccountDto accountDto) {
        Optional<Account> savedAccount = accountService.findById(id);
        if (savedAccount.isPresent()) {
            Account accountToUpdate = modelMapper.map(accountDto, Account.class);
            accountToUpdate.setId(id);
            accountService.save(accountToUpdate);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Traite les requêtes DELETE pour supprimer un compte
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable long id) {
        Optional<Account> savedAccount = accountService.findById(id);
        if (savedAccount.isPresent()) {
            accountService.delete(savedAccount.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
