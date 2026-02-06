package com.agendamento.AgendamentoBarbearia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record CreateClientDTO(
        @NotBlank String username,

        @NotBlank @Email String email,

        @NotBlank
        @Size(min = 6, max = 128)
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[^a-zA-Z0-9]).+$",
                message = "Deve conter pelo menos um número e um caractere especial"
        )
        String password,

        @NotBlank String nome,

        @NotBlank @Pattern(regexp = "^\\d{11}$", message = "Telefone deve conter DDD (2 dígitos) + 9 dígitos")
        String phone
) {
}
