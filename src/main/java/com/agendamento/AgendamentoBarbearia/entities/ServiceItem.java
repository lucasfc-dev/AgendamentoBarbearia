package com.agendamento.AgendamentoBarbearia.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

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


}
