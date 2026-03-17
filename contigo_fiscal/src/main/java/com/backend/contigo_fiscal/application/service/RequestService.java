package com.backend.contigo_fiscal.application.service;

import com.backend.contigo_fiscal.infra.persistence.entity.RequestEntity;
import com.backend.contigo_fiscal.infra.persistence.repository.RequestRepository;
import com.backend.contigo_fiscal.web.dto.CreateRequestDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;

    /**
     * Crea una nueva solicitud de prospecto capturada desde el chatbot.
     * Se guarda con el estado inicial "nuevo" para la gestión del contador[cite: 5].
     */
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

public List<RequestEntity> findAll() {
    
    return requestRepository.findAll(); 
}

public void updateStatus(UUID id, String status) {
    requestRepository.findById(id).ifPresent(entity -> {
        entity.setStatus(status.toLowerCase());
        requestRepository.save(entity);
    });
}
}