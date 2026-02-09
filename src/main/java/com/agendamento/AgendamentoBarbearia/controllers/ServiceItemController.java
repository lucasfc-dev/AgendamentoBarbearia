package com.agendamento.AgendamentoBarbearia.controllers;

import com.agendamento.AgendamentoBarbearia.dto.CreateServiceItemDTO;
import com.agendamento.AgendamentoBarbearia.dto.ServiceItemResponseDTO;
import com.agendamento.AgendamentoBarbearia.entities.ServiceItem;
import com.agendamento.AgendamentoBarbearia.services.ServiceItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/service-item")
public class ServiceItemController {
    private final ServiceItemService serviceItemService;

    public ServiceItemController(ServiceItemService serviceItemService){
        this.serviceItemService = serviceItemService;
    }

    @PostMapping
    public ResponseEntity<ServiceItemResponseDTO> createServiceItem(CreateServiceItemDTO serviceItemDTO){
        ServiceItem serviceItem = serviceItemService.createServiceItem(serviceItemDTO);
        return ResponseEntity.ok().body(ServiceItemResponseDTO.from(serviceItem));
    }

    @GetMapping
    public ResponseEntity<List<ServiceItemResponseDTO>> getServices(){
        List<ServiceItem> services = serviceItemService.getServices();
        return ResponseEntity.ok().body(services.stream().map(service -> ServiceItemResponseDTO.from(service)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceItemResponseDTO> getService(@PathVariable UUID id){
        ServiceItem service = serviceItemService.getServiceById(id);
        return ResponseEntity.ok().body(ServiceItemResponseDTO.from(service));
    }

}
