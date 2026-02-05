package com.agendamento.AgendamentoBarbearia.entities;

import com.agendamento.AgendamentoBarbearia.config.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "barber_id",nullable = false)
    private Barber barber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id",nullable = false)
    private Client client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_item_id",nullable = false)
    private ServiceItem serviceItem;

    @Column(name = "scheduled_at", nullable = false)
    private Instant scheduledAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}
