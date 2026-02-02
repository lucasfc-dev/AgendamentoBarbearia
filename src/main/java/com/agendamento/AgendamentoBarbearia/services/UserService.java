package com.agendamento.AgendamentoBarbearia.services;

import com.agendamento.AgendamentoBarbearia.dto.CreateUserDTO;
import com.agendamento.AgendamentoBarbearia.entities.User;
import com.agendamento.AgendamentoBarbearia.enums.Role;
import com.agendamento.AgendamentoBarbearia.exceptions.classes.BusinessException;
import com.agendamento.AgendamentoBarbearia.exceptions.classes.NotFoundException;
import com.agendamento.AgendamentoBarbearia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private User userFactory(CreateUserDTO userData, Role role){
        User user = new User();
        user.setUsername(userData.getUsername());
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        user.setEmail(userData.getEmail());
        user.setRole(role);
        userRepository.save(user);
        return user;
    }

    public User createUser(CreateUserDTO userData) throws BusinessException{
        if(userRepository.existsByUsername(userData.getUsername())){
            throw new BusinessException("Nome de usuário já existe");
        }
        if(userRepository.existsByEmail(userData.getEmail())){
            throw new BusinessException("Email já cadastrado");
        }

        return userFactory(userData,Role.USER);
    }

    public User createAdmin(CreateUserDTO userData) throws BusinessException{
        if(userRepository.existsByUsername(userData.getUsername())){
            throw new BusinessException("Nome de usuário já existe");
        }
        if(userRepository.existsByEmail(userData.getEmail())){
            throw new BusinessException("Email já cadastrado");
        }

        return userFactory(userData,Role.ADMIN);
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
