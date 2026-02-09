package com.agendamento.AgendamentoBarbearia.dto;

import com.agendamento.AgendamentoBarbearia.entities.ServiceItem;

import java.time.Instant;
import java.util.UUID;

public record ServiceItemResponseDTO(
        UUID id,
        String name,
        Instant createdAt
) {
    public static ServiceItemResponseDTO from(ServiceItem serviceItem){
        return new ServiceItemResponseDTO(
                serviceItem.getId(),
                serviceItem.getName(),
                serviceItem.getCreatedAt()
        );
    }
}
