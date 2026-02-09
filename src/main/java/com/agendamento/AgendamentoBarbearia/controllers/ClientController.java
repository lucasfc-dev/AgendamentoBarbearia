package com.agendamento.AgendamentoBarbearia.controllers;

import com.agendamento.AgendamentoBarbearia.dto.ClientResponseDTO;
import com.agendamento.AgendamentoBarbearia.dto.CreateClientDTO;
import com.agendamento.AgendamentoBarbearia.entities.Client;
import com.agendamento.AgendamentoBarbearia.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService=clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody CreateClientDTO clientDTO){
        Client client = clientService.createClient(clientDTO);
        return ResponseEntity.ok(ClientResponseDTO.from(client));
    }
}
