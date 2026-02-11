package com.agendamento.AgendamentoBarbearia.services;

import com.agendamento.AgendamentoBarbearia.config.enums.AppointmentStatus;
import com.agendamento.AgendamentoBarbearia.dto.CreateAppointmentDTO;
import com.agendamento.AgendamentoBarbearia.entities.Appointment;
import com.agendamento.AgendamentoBarbearia.entities.Barber;
import com.agendamento.AgendamentoBarbearia.entities.Client;
import com.agendamento.AgendamentoBarbearia.entities.ServiceItem;
import com.agendamento.AgendamentoBarbearia.exceptions.classes.ConflictException;
import com.agendamento.AgendamentoBarbearia.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final ClientService clientService;
    private final BarberService barberService;
    private final ServiceItemService itemService;

    public AppointmentService(AppointmentRepository appointmentRepository, ClientService clientService, BarberService barberService, ServiceItemService itemService ){
        this.appointmentRepository = appointmentRepository;
        this.clientService = clientService;
        this.barberService = barberService;
        this.itemService = itemService;
    }

    public Appointment createAppointment(CreateAppointmentDTO appointmentData){
        Client client = clientService.getClientById(appointmentData.clientId());
        Barber barber = barberService.getBarberById(appointmentData.barberId());
        ServiceItem serviceItem = itemService.getServiceById(appointmentData.serviceItemId());

        validateSchedule(client, barber, appointmentData.scheduledAt(), serviceItem.getDurationMinutes());

        Appointment appointment = new Appointment();
        appointment.setClient(client);
        appointment.setBarber(barber);
        appointment.setServiceItem(serviceItem);
        appointment.setScheduledAt(appointmentData.scheduledAt());
        appointment.setStatus(AppointmentStatus.PENDING);

        return appointmentRepository.save(appointment);
    }

    private void validateSchedule(Client client, Barber barber, Instant scheduleAt, Integer durationMinutes){
        Instant endTime = scheduleAt.plus(durationMinutes, ChronoUnit.MINUTES);
        if (appointmentRepository.existsBarberScheduleConflict(barber, scheduleAt, endTime)){
            throw new ConflictException("Barbeiro tem outro agendamento no horário");
        }
        if (appointmentRepository.existsClientScheduleConflict(client, scheduleAt, endTime)){
            throw new ConflictException("Cliente tem outro agendamento no horário");
        }
    }

}
