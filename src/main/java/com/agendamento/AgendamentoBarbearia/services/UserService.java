package com.agendamento.AgendamentoBarbearia.services;

import com.agendamento.AgendamentoBarbearia.config.security.constants.Roles;
import com.agendamento.AgendamentoBarbearia.dto.CreateClientDTO;
import com.agendamento.AgendamentoBarbearia.dto.CreateUserDTO;
import com.agendamento.AgendamentoBarbearia.entities.User;
import com.agendamento.AgendamentoBarbearia.entities.Role;
import com.agendamento.AgendamentoBarbearia.exceptions.classes.BusinessException;
import com.agendamento.AgendamentoBarbearia.exceptions.classes.NotFoundException;
import com.agendamento.AgendamentoBarbearia.repositories.RoleRepository;
import com.agendamento.AgendamentoBarbearia.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private User userFactory(CreateClientDTO userData, String roleName){
        if(userRepository.existsByUsername(userData.getUsername())){
            throw new BusinessException("Nome de usuário já existe");
        }
        if(userRepository.existsByEmail(userData.getEmail())){
            throw new BusinessException("Email já cadastrado");
        }
        User user = new User();
        user.setUsername(userData.getUsername());
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        user.setEmail(userData.getEmail());
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new NotFoundException("Role não encontrada"));
        user.getRoles().add(role);
        return user;
    }

    public User createClientUser(CreateClientDTO userData) throws BusinessException{
        User client = userFactory(userData, Roles.CLIENT);
        return userRepository.save(client);
    }

    public User createBarberUser(CreateUserDTO userData)throws  BusinessException{
        User barber = userFactory(userData,Roles.BARBER);
        return userRepository.save(barber);
    }

    public User createAdminUser(CreateUserDTO userData) throws BusinessException{
        User admin = userFactory(userData,Roles.ADMIN);
        return userRepository.save(admin);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(UUID id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    public User getUserByUsername(String username) throws  NotFoundException{
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }
}
