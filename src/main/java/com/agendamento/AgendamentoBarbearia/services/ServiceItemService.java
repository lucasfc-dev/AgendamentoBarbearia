package com.agendamento.AgendamentoBarbearia.services;

import com.agendamento.AgendamentoBarbearia.dto.CreateServiceItemDTO;
import com.agendamento.AgendamentoBarbearia.entities.ServiceItem;
import com.agendamento.AgendamentoBarbearia.exceptions.classes.NotFoundException;
import com.agendamento.AgendamentoBarbearia.repositories.ServiceItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceItemService {
    private final ServiceItemRepository serviceItemRepository;

    public ServiceItemService(ServiceItemRepository serviceItemRepository){
        this.serviceItemRepository = serviceItemRepository;
    }

    public ServiceItem createServiceItem(CreateServiceItemDTO serviceData){
        ServiceItem serviceItem = new ServiceItem();
        serviceItem.setName(serviceData.name());
        serviceItem.setCost(serviceData.cost());
        return serviceItemRepository.save(serviceItem);
    }

    public List<ServiceItem> getServices(){
        return serviceItemRepository.findAll();
    }

    public ServiceItem getServiceById(UUID id) throws NotFoundException{
        return serviceItemRepository.findById(id).orElseThrow(() -> new NotFoundException("Serviço não encontrado"));
    }
}
