package com.agendamento.AgendamentoBarbearia.config.initializer;

import com.agendamento.AgendamentoBarbearia.config.enums.Roles;
import com.agendamento.AgendamentoBarbearia.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import com.agendamento.AgendamentoBarbearia.entities.Role;



@Component
@RequiredArgsConstructor
@Order(1)
public class RoleInitializer implements  ApplicationRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) {
        seed(Roles.ROLE_ADMIN.name());
        seed(Roles.ROLE_BARBER.name());
        seed(Roles.ROLE_CLIENT.name());
    }

    private void seed(String name) {
        roleRepository.findByName(name)
                .orElseGet(() -> roleRepository.save(new Role(null, name)));
    }
}
