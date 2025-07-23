package fr.afpa.orm.mappers;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import fr.afpa.orm.dto.AccountDto;
import fr.afpa.orm.dto.ClientDto;
import fr.afpa.orm.entities.Client;

@Service
public class ClientDtoMapper implements Function<Client, ClientDto> {

    @Override
    public ClientDto apply(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setEmail(client.getEmail());
        clientDto.setBirthdate(client.getBirthdate());
        List<AccountDto> accountDtos = client.getAccounts().stream()
                .map(account -> {
                    AccountDto dto = new AccountDto();
                    dto.setId(account.getId());
                    dto.setBalance(account.getBalance());
                    dto.setCreationTime(account.getCreationTime());
                    dto.setClient(null);
                    return dto;
                })
                .toList();
        clientDto.setAccounts(accountDtos);
        return clientDto;
    }
}
