package com.agendamento.AgendamentoBarbearia.dto;

import java.time.Instant;
import java.util.UUID;

public record CreateAppointmentDTO(
        UUID serviceItemId,
        UUID clientId,
        UUID barberId,
        Instant scheduledAt
) {
}
