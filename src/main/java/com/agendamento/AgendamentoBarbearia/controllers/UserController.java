package com.agendamento.AgendamentoBarbearia.controllers;

import com.agendamento.AgendamentoBarbearia.dto.CreateUserDTO;
import com.agendamento.AgendamentoBarbearia.dto.UserResponseDTO;
import com.agendamento.AgendamentoBarbearia.entities.User;
import com.agendamento.AgendamentoBarbearia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserDTO userData){
        User createdUser = userService.createUser(userData);
        return ResponseEntity.ok(UserResponseDTO.from(createdUser));
    }

    @PostMapping("/admin")
    public ResponseEntity<UserResponseDTO> createAdmin(@RequestBody CreateUserDTO userData){
        User createdUser = userService.createAdmin(userData);
        return ResponseEntity.ok(UserResponseDTO.from(createdUser));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponseDTO>> getUsers(){
        List<User> users = userService.getUsers();
        List<UserResponseDTO> userResponses = users.stream()
                .map(UserResponseDTO::from)
                .toList();
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserResponseDTO.from(user));
    }

}
