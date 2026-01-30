package com.agendamento.AgendamentoBarbearia.controllers;

import com.agendamento.AgendamentoBarbearia.dto.LoginDTO;
import com.agendamento.AgendamentoBarbearia.dto.UserResponseDTO;
import com.agendamento.AgendamentoBarbearia.entities.User;
import com.agendamento.AgendamentoBarbearia.services.AuthenticationService;
import com.agendamento.AgendamentoBarbearia.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginData){
        var auth = new UsernamePasswordAuthenticationToken(loginData.getUsername(),loginData.getPassword());
        authenticationManager.authenticate(auth);
        return jwtService.generateToken(loginData.getUsername());
    }

    //O Spring injeta automaticamente a instância de Authentication criada no SecurityFilter;
    @GetMapping("/current_authenticated")
    public ResponseEntity<UserResponseDTO> getCurrentAuthenticated(Authentication authentication){
        User authenticatedUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(UserResponseDTO.from(authenticatedUser));
    }
}
