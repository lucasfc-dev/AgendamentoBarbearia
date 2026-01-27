package com.agendamento.AgendamentoBarbearia.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {
    String username;
    String email;
    String password;
}
