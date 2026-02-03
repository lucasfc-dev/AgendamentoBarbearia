package com.agendamento.AgendamentoBarbearia.dto;

import com.agendamento.AgendamentoBarbearia.entities.Client;

import java.util.UUID;

public record ClientResponseDTO(
        UUID userId,
        String username,
        String email,
        String phone
) {
    public static ClientResponseDTO from(Client client){
        return new ClientResponseDTO(
                client.getUser().getId(),
                client.getUser().getUsername(),
                client.getUser().getEmail(),
                client.getPhone()
        );
    }
}
