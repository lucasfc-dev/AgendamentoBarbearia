package com.agendamento.AgendamentoBarbearia.enums;

import lombok.Getter;

@Getter
public enum Role {
    USER,
    ADMIN;


    private String roleName;

    public String getRoleName(){
        return this.roleName;
    }
}
