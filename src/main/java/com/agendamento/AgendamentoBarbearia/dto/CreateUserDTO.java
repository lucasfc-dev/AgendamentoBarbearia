package com.agendamento.AgendamentoBarbearia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserDTO {
    String username;
    String email;
    String password;
}
