package fr.afpa.orm.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.afpa.orm.dto.ClientDto;
import fr.afpa.orm.mappers.ClientDtoMapper;
import fr.afpa.orm.repositories.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientDtoMapper clientDtoMapper;

    public ClientService(ClientRepository clientRepository, ClientDtoMapper clientDtoMapper) {
        this.clientRepository = clientRepository;
        this.clientDtoMapper = clientDtoMapper;
    }

    public List<ClientDto> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientDtoMapper::apply)
                .collect(Collectors.toList());
    }
}
