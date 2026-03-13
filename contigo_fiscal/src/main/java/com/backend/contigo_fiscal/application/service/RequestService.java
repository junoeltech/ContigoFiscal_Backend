package com.backend.contigo_fiscal.application.service;


import com.backend.contigo_fiscal.infra.persistence.entity.RequestEntity;
import com.backend.contigo_fiscal.infra.persistence.repository.RequestRepository;
import com.backend.contigo_fiscal.web.dto.CreateRequestDTO;
import com.backend.contigo_fiscal.web.dto.DocumentItemDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final ObjectMapper objectMapper;

    public RequestEntity create(CreateRequestDTO dto) {

        RequestEntity entity = RequestEntity.builder()
                .nombreCompleto(dto.getNombreCompleto())
                .correo(dto.getCorreo())
                .telefono(dto.getTelefono())
                .rfc(dto.getRfc())
                .tipoContribuyente(dto.getTipoContribuyente())
                .servicioId(dto.getServicioId())
                .servicioText(dto.getServicioText())
                .descripcionBreve(dto.getDescripcionBreve())

                // campos default
                .status("NEW")
                .prioridad("MEDIA")
                .score(0)
                .source("API")

                // documentos
                .documentos(convertDocumentos(dto.getDocumentos()))

                .build();

        return requestRepository.save(entity);
    }

    private List<Object> convertDocumentos(List<DocumentItemDTO> documentos) {

        if (documentos == null) {
            return null;
        }

        return documentos.stream()
                .map(item -> objectMapper.convertValue(
                        item,
                        new TypeReference<Map<String, Object>>() {}
                ))
                .map(map -> (Object) map)
                .collect(Collectors.toList());
    }
}