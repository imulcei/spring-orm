package fr.afpa.orm.mappers;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.afpa.orm.dto.AccountDto;
import fr.afpa.orm.dto.ClientDto;
import fr.afpa.orm.entities.Account;

@Service
public class AccountDtoMapper implements Function<Account, AccountDto> {

    @Override
    public AccountDto apply(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setCreationTime(account.getCreationTime());
        accountDto.setBalance(account.getBalance());

        if (account.getOwner() != null) {
            ClientDto clientDto = new ClientDto();
            clientDto.setId(account.getOwner().getId());
            clientDto.setFirstName(account.getOwner().getFirstName());
            clientDto.setLastName(account.getOwner().getLastName());
            clientDto.setEmail(account.getOwner().getEmail());
            clientDto.setBirthdate(account.getOwner().getBirthdate());
            List<AccountDto> accountDtos = account.getOwner().getAccounts().stream()
                    .map(a -> {
                        AccountDto accountClientDto = new AccountDto();
                        accountClientDto.setId(a.getId());
                        accountClientDto.setBalance(a.getBalance());
                        accountClientDto.setCreationTime(a.getCreationTime());
                        accountClientDto.setClient(null);
                        return accountClientDto;
                    }).collect(Collectors.toList());

            clientDto.setAccounts(accountDtos);
            accountDto.setClient(clientDto);
        }

        return accountDto;
    }
}
