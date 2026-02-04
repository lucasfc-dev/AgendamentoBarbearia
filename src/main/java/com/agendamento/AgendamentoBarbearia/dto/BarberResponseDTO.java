package com.agendamento.AgendamentoBarbearia.dto;

import com.agendamento.AgendamentoBarbearia.entities.Barber;

import java.util.UUID;

public record BarberResponseDTO(
        UUID id,
        UUID userId,
        String username,
        String email,
        String phone
) {
    public static BarberResponseDTO from(Barber barber){
        return new BarberResponseDTO(
                barber.getId(),
                barber.getUser().getId(),
                barber.getUser().getUsername(),
                barber.getUser().getEmail(),
                barber.getPhone()
        );
    }
}
