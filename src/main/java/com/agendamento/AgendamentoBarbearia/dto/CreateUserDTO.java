package com.agendamento.AgendamentoBarbearia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
        @NotBlank String username,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8, max = 128) String password
) {
}
