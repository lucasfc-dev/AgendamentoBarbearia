package com.agendamento.AgendamentoBarbearia.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record CreateServiceItemDTO(
        String name,
        BigDecimal cost,
        Integer durationMinutes,
        Instant createdAt
) {
}
