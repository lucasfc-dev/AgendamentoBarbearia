package com.agendamento.AgendamentoBarbearia.services;

import com.agendamento.AgendamentoBarbearia.config.enums.Roles;
import com.agendamento.AgendamentoBarbearia.dto.CreateUserDTO;
import com.agendamento.AgendamentoBarbearia.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminService {
    private final UserService userService;

    public AdminService(UserService userService){
        this.userService = userService;
    }

    public User createAdminUser(CreateUserDTO adminData){
        return userService.createAdminUser(adminData);
    }

    public List<User> getAllAdmins(){
        return userService.getUsersByRoleName(Roles.ROLE_ADMIN);
    }

    public User getAdminById(UUID id){
        return userService.getUserById(id);
    }

}
