package com.agendamento.AgendamentoBarbearia.services;

import com.agendamento.AgendamentoBarbearia.dto.CreateBarberDTO;
import com.agendamento.AgendamentoBarbearia.entities.Barber;
import com.agendamento.AgendamentoBarbearia.entities.User;
import com.agendamento.AgendamentoBarbearia.exceptions.classes.MediaUploadException;
import com.agendamento.AgendamentoBarbearia.exceptions.classes.NotFoundException;
import com.agendamento.AgendamentoBarbearia.repositories.BarberRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BarberService {
    private final BarberRepository barberRepository;
    private final UserService userService;

    public BarberService(BarberRepository barberRepository, UserService userService) {
        this.barberRepository = barberRepository;
        this.userService = userService;
    }

    public Barber createBarber(CreateBarberDTO barberDTO) throws MediaUploadException {
        User user = userService.createBarberUser(barberDTO);
        Barber barber = new Barber();
        barber.setUser(user);
        barber.setPhone(barberDTO.phone());
        try {
            barber.setPhoto(barberDTO.photo().getBytes());
        } catch (IOException ex) {
            throw new MediaUploadException("Erro ao carregar foto de Barber", ex);
        }
        return barberRepository.save(barber);
    }

    public byte[] getBarberPhoto(UUID id) throws NotFoundException {
        Barber barber = barberRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Barber não encontrado")
        );
        return barber.getPhoto();
    }

    public List<Barber> getBarbers(){
        return barberRepository.findAll();
    }

    public Barber getBarberById(UUID id) throws NotFoundException{
        return barberRepository.findById(id).orElseThrow(() -> new NotFoundException("Barber não encontrado"));
    }
}
