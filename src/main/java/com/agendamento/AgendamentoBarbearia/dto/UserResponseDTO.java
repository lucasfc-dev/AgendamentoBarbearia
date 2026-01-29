package com.agendamento.AgendamentoBarbearia.dto;

import com.agendamento.AgendamentoBarbearia.entities.User;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String username,
        String email
) {
    public static UserResponseDTO from(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
