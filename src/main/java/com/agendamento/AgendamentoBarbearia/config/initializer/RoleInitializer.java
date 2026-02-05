package com.agendamento.AgendamentoBarbearia.config.initializer;

import com.agendamento.AgendamentoBarbearia.config.enums.Roles;
import com.agendamento.AgendamentoBarbearia.entities.Role;
import com.agendamento.AgendamentoBarbearia.repositories.RoleRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Component
@Order(1)
public class RoleInitializer implements  ApplicationRunner {
    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(@NonNull ApplicationArguments args) {
        seed(Roles.ROLE_ADMIN.name());
        seed(Roles.ROLE_BARBER.name());
        seed(Roles.ROLE_CLIENT.name());
    }

    private void seed(String name) {
        roleRepository.findByName(name)
                .orElseGet(() -> roleRepository.save(new Role(name)));
    }
}
