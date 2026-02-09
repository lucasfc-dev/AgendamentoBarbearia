package com.agendamento.AgendamentoBarbearia.dto;

import com.agendamento.AgendamentoBarbearia.entities.User;

import java.time.Instant;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String username,
        String email,
        Instant createdAt
) {
    public static UserResponseDTO from(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
