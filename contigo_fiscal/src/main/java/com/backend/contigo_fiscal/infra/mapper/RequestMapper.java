package com.backend.contigo_fiscal.infra.mapper;

import com.backend.contigo_fiscal.infra.persistence.entity.RequestEntity;
import com.backend.contigo_fiscal.web.dto.CreateRequestDTO;
import com.backend.contigo_fiscal.web.dto.RequestResponseDTO;
import com.backend.contigo_fiscal.web.dto.RequestSummaryDTO;

import java.util.UUID;

public final class RequestMapper {

    private RequestMapper() {}

    public static RequestEntity toEntity(CreateRequestDTO dto) {
        if (dto == null) return null;

        return RequestEntity.builder()
                .id(UUID.randomUUID())
                .nombreCompleto(dto.getNombreCompleto())
                .correo(dto.getCorreo())
                .telefono(dto.getTelefono())
                .rfc(dto.getRfc())
                .tipoContribuyente(dto.getTipoContribuyente())
                .servicioId(dto.getServicioId())
                .status("nuevo") 
                .build();
    }

    public static RequestResponseDTO toResponse(RequestEntity entity) {
        if (entity == null) return null;
        RequestResponseDTO dto = new RequestResponseDTO();
        dto.setId(entity.getId());
        
        return dto;
    }

    public static RequestSummaryDTO toSummary(RequestEntity entity) {
    if (entity == null) return null;
    
    RequestSummaryDTO s = new RequestSummaryDTO();
    s.setId(entity.getId());
    s.setNombreCompleto(entity.getNombreCompleto());
    s.setServicioId(entity.getServicioId()); 
    s.setRfc(entity.getRfc());
    s.setTelefono(entity.getTelefono());
    s.setCorreo(entity.getCorreo());
    s.setTipoContribuyente(entity.getTipoContribuyente()); 
    s.setStatus(entity.getStatus()); 
    s.setCreatedAt(entity.getCreatedAt());
    
    return s;
}
}