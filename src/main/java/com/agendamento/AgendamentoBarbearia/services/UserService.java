package com.agendamento.AgendamentoBarbearia.services;

import com.agendamento.AgendamentoBarbearia.config.constants.Roles;
import com.agendamento.AgendamentoBarbearia.dto.CreateBarberDTO;
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

    private User userFactory(String username, String email, String rawPassword, String roleName){
        if(userRepository.existsByUsername(username)){
            throw new BusinessException("Nome de usuário já existe");
        }
        if(userRepository.existsByEmail(email)){
            throw new BusinessException("Email já cadastrado");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setEmail(email);
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new NotFoundException("Role não encontrada"));
        user.getRoles().add(role);
        return user;
    }

    public User createClientUser(CreateClientDTO userData) throws BusinessException{
        User client = userFactory(userData.username(), userData.email(), userData.password(), Roles.CLIENT);
        return userRepository.save(client);
    }

    public User createBarberUser(CreateBarberDTO userData)throws  BusinessException{
        User barber = userFactory(userData.username(), userData.email(), userData.password(), Roles.BARBER);
        return userRepository.save(barber);
    }

    public User createAdminUser(CreateUserDTO userData) throws BusinessException{
        User admin = userFactory(userData.username(), userData.email(), userData.password(), Roles.ADMIN);
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

    public void createAdminIfNotExists(CreateUserDTO dto) {
        if (userRepository.existsByUsername(dto.username())) {
            return;
        }

        User admin = userFactory(
                dto.username(),
                dto.email(),
                dto.password(),
                Roles.ADMIN
        );

        userRepository.save(admin);
    }
}
