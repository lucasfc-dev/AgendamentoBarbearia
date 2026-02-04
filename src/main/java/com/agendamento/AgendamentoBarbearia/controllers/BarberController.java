package com.agendamento.AgendamentoBarbearia.controllers;

import com.agendamento.AgendamentoBarbearia.dto.BarberResponseDTO;
import com.agendamento.AgendamentoBarbearia.dto.CreateBarberDTO;
import com.agendamento.AgendamentoBarbearia.entities.Barber;
import com.agendamento.AgendamentoBarbearia.repositories.BarberRepository;
import com.agendamento.AgendamentoBarbearia.services.BarberService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/barber")
public class BarberController {
    private BarberService barberService;

    public BarberController(BarberService barberService) {
        this.barberService = barberService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BarberResponseDTO> createBarber(@ModelAttribute CreateBarberDTO barberDTO){
        Barber barber = barberService.createBarber(barberDTO);
        return ResponseEntity.ok().body(BarberResponseDTO.from(barber));
    }

    @GetMapping("/{id}/download-photo")
    public ResponseEntity<byte[]> getBarberPhoto(@PathVariable UUID id){
        byte[] barberPhoto = barberService.getBarberPhoto(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(barberPhoto);
    }
}
