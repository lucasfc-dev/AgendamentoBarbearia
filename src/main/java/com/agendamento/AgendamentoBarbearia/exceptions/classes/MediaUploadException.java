package com.agendamento.AgendamentoBarbearia.exceptions.classes;

public class MediaUploadException extends RuntimeException {
    public MediaUploadException(String message, Throwable ex) {
        super(message, ex);
    }
}
