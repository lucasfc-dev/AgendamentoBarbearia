package com.agendamento.AgendamentoBarbearia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
        @NotBlank String username,

        @NotBlank @Email String email,

        @NotBlank
        @Size(min = 6, max = 128)
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[^a-zA-Z0-9]).+$",
                message = "Deve conter pelo menos um número e um caractere especial"
        )
        String password
) {
}
