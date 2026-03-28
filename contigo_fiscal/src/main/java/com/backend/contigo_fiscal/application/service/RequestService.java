package com.backend.contigo_fiscal.application.service;

import com.backend.contigo_fiscal.infra.mapper.RequestMapper;
import com.backend.contigo_fiscal.infra.persistence.entity.RequestEntity;
import com.backend.contigo_fiscal.infra.persistence.repository.RequestRepository;
import com.backend.contigo_fiscal.infra.persistence.repository.ServiceRepository;
import com.backend.contigo_fiscal.web.dto.CreateRequestDTO;
import com.backend.contigo_fiscal.web.dto.RequestSummaryDTO;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final ServiceRepository serviceRepository;


    public RequestEntity create(CreateRequestDTO dto) {

        RequestEntity entity = RequestEntity.builder()
                .nombreCompleto(dto.getNombreCompleto())
                .correo(dto.getCorreo())
                .telefono(dto.getTelefono())
                .rfc(dto.getRfc())
                .tipoContribuyente(dto.getTipoContribuyente())
                .servicioId(dto.getServicioId())
                .status("nuevo") 
                .build();

        return requestRepository.save(entity);
    }

public List<RequestSummaryDTO> findAll() {
    return requestRepository.findAll().stream().map(entity -> {
        RequestSummaryDTO dto = RequestMapper.toSummary(entity);
        
        // Validamos que el ID no sea nulo antes de buscar en el repositorio
        if (entity.getServicioId() != null) {
            String nombreReal = serviceRepository.findById(entity.getServicioId())
                    .map(service -> service.getName())
                    .orElse("Servicio no encontrado");
            dto.setNombreServicio(nombreReal);
        } else {
            dto.setNombreServicio("Sin servicio asignado");
        }
        
        return dto;
    }).collect(Collectors.toList());
}

public void updateStatus(UUID id, String status) {
    requestRepository.findById(id).ifPresent(entity -> {
        entity.setStatus(status.toLowerCase());
        requestRepository.save(entity);
    });
}

public void delete(UUID id) {
    requestRepository.deleteById(id);
}
}