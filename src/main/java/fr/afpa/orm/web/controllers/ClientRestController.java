package fr.afpa.orm.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.orm.dto.ClientDto;
// import fr.afpa.orm.mappers.ClientDtoMapper;
import fr.afpa.orm.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    // @Autowired
    // private ClientDtoMapper clientMapper;

    public ClientRestController() {

    }

    @GetMapping
    public List<ClientDto> getAll() {
        return clientService.findAll();
    }
}
