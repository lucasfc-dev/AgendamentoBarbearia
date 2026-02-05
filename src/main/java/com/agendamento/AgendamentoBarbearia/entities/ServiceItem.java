package com.agendamento.AgendamentoBarbearia.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service_item")
@Entity
public class ServiceItem {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;

    @Column(precision = 10, scale = 2)
    private BigDecimal cost;

    @Column(nullable = false)
    private Boolean active = true;
}
