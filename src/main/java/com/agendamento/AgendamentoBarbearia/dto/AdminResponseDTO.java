package com.agendamento.AgendamentoBarbearia.dto;

import com.agendamento.AgendamentoBarbearia.entities.Role;
import com.agendamento.AgendamentoBarbearia.entities.User;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record AdminResponseDTO(
        UUID id,
        String username,
        String email,
        Set<Role> roles,
        Instant createdAt
) {
    public static AdminResponseDTO from(User user){
        return new AdminResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles(),
                user.getCreatedAt()
        );
    }
}