package com.agendamento.AgendamentoBarbearia.services;

import com.agendamento.AgendamentoBarbearia.dto.CreateClientDTO;
import com.agendamento.AgendamentoBarbearia.entities.Client;
import com.agendamento.AgendamentoBarbearia.entities.User;
import com.agendamento.AgendamentoBarbearia.exceptions.classes.NotFoundException;
import com.agendamento.AgendamentoBarbearia.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    private final UserService userService;
    private final ClientRepository clientRepository;

    public ClientService(UserService userService, ClientRepository clientRepository){
        this.userService = userService;
        this.clientRepository = clientRepository;
    }

    public Client createClient(CreateClientDTO clientDTO){
        User user = userService.createClientUser(clientDTO);
        Client client = new Client();
        client.setUser(user);
        client.setPhone(clientDTO.phone());
        clientRepository.save(client);
        return client;
    }

    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    public Client getClientById(UUID id) throws  NotFoundException{
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client não encontrado"));
    }

}
