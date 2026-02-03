package com.agendamento.AgendamentoBarbearia.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class CreateClientDTO {
    String username;
    String email;
    String password;
    String nome;
    MultipartFile img;
    String phone;
}
