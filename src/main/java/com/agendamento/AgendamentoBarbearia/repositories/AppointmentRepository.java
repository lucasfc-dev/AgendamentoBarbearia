package com.agendamento.AgendamentoBarbearia.repositories;

import com.agendamento.AgendamentoBarbearia.entities.Appointment;
import com.agendamento.AgendamentoBarbearia.entities.Barber;
import com.agendamento.AgendamentoBarbearia.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    @Query("""
    SELECT COUNT(a) > 0
    FROM Appointment a
    WHERE a.barber = :barber
      AND a.startTime < :endTime
      AND a.endTime > :startTime
""")
    boolean existsBarberScheduleConflict(
            @Param("barber") Barber barber,
            @Param("startTime") Instant startTime,
            @Param("endTime") Instant endTime
    );

    @Query("""
        SELECT COUNT(a) > 0
        FROM Appointment a
        WHERE a.client = :client
          AND a.startTime < :endTime
          AND a.endTime > :startTime
    """)
    boolean existsClientScheduleConflict(
            @Param("client") Client client,
            @Param("startTime") Instant startTime,
            @Param("endTime") Instant endTime
    );

}
