package com.agendamento.AgendamentoBarbearia.enums;

import lombok.Getter;

@Getter
public enum Role {
    USER_ROLE,
    ADMIN_ROLE;


    private String roleName;

    public String getRoleName(){
        return this.roleName;
    }
}
