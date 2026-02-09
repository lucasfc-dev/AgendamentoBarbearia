package com.agendamento.AgendamentoBarbearia.controllers;

import com.agendamento.AgendamentoBarbearia.dto.AdminResponseDTO;
import com.agendamento.AgendamentoBarbearia.dto.CreateUserDTO;
import com.agendamento.AgendamentoBarbearia.entities.User;
import com.agendamento.AgendamentoBarbearia.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<AdminResponseDTO> createAdmin(CreateUserDTO adminData){
        User user = adminService.createAdminUser(adminData);
        return ResponseEntity.ok().body(AdminResponseDTO.from(user));
    }

    @GetMapping
    public ResponseEntity<List<AdminResponseDTO>> getAllAdmins(){
        List<AdminResponseDTO> admins = adminService.getAllAdmins().stream().map(
                admin -> AdminResponseDTO.from(admin)
        ).toList();
        return ResponseEntity.ok().body(admins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponseDTO> getAdminById(@PathVariable UUID id){
        User admin = adminService.getAdminById(id);
        return ResponseEntity.ok().body(AdminResponseDTO.from(admin));
    }

}
