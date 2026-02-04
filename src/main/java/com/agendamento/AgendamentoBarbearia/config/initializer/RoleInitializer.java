package com.agendamento.AgendamentoBarbearia.config.initializer;

import com.agendamento.AgendamentoBarbearia.config.constants.Roles;
import com.agendamento.AgendamentoBarbearia.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import com.agendamento.AgendamentoBarbearia.entities.Role;



@Component
@RequiredArgsConstructor
public class RoleInitializer implements  ApplicationRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) {
        seed(Roles.ADMIN);
        seed(Roles.BARBER);
        seed(Roles.CLIENT);
    }

    private void seed(String name) {
        roleRepository.findByName(name)
                .orElseGet(() -> roleRepository.save(new Role(null, name)));
    }
}
