package com.agendamento.AgendamentoBarbearia.exceptions.handlers;

import com.agendamento.AgendamentoBarbearia.exceptions.classes.BusinessException;
import com.agendamento.AgendamentoBarbearia.exceptions.classes.ConflictException;
import com.agendamento.AgendamentoBarbearia.exceptions.classes.MediaUploadException;
import com.agendamento.AgendamentoBarbearia.exceptions.classes.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> businessException(BusinessException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler({UsernameNotFoundException.class, NotFoundException.class})
    public ResponseEntity<String> notFoundException(UsernameNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MediaUploadException.class)
    public ResponseEntity<String> mediaUploadException(MediaUploadException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> conflictException(ConflictException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}