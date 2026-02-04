package com.agendamento.AgendamentoBarbearia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.web.multipart.MultipartFile;

public record CreateBarberDTO(
        @NotBlank String username,
        @NotBlank @Email String email,
        @NotBlank @DecimalMin("1") @Size(min = 8, max = 128) String password,
        @NotBlank String nome,
        @NotBlank @Pattern(regexp = "^\\d{11}$", message = "Telefone deve conter DDD (2 dígitos) + 9 dígitos") String phone,
        @NotBlank MultipartFile photo
) {}
