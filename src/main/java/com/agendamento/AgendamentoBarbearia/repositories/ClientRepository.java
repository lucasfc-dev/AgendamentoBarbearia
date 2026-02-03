package com.agendamento.AgendamentoBarbearia.repositories;

import com.agendamento.AgendamentoBarbearia.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID>{

}
