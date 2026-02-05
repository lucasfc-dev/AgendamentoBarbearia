package com.agendamento.AgendamentoBarbearia.config.initializer;

import com.agendamento.AgendamentoBarbearia.config.properties.AdminProperties;
import com.agendamento.AgendamentoBarbearia.dto.CreateUserDTO;
import com.agendamento.AgendamentoBarbearia.services.UserService;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class AdminInitializer implements ApplicationRunner{
    private final UserService userService;
    private final AdminProperties adminProperties;

    public AdminInitializer(UserService userService,AdminProperties adminProperties){
        this.userService = userService;
        this.adminProperties = adminProperties;
    }

    @Override
    public void run(@NonNull ApplicationArguments args) throws Exception {
        seedAdminUser();
    }

    private void seedAdminUser(){
        CreateUserDTO adminData = new CreateUserDTO(
                adminProperties.getUsername(),
                adminProperties.getEmail(),
                adminProperties.getPassword()
        );
        userService.createAdminIfNotExists(adminData);
    }
}
