package com.agendamento.AgendamentoBarbearia.exceptions.classes;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}
