package com.backend.contigo_fiscal.infra.mapper;

import com.backend.contigo_fiscal.infra.persistence.entity.RequestEntity;
import com.backend.contigo_fiscal.web.dto.CreateRequestDTO;
import com.backend.contigo_fiscal.web.dto.DocumentItemDTO;
import com.backend.contigo_fiscal.web.dto.RequestResponseDTO;
import com.backend.contigo_fiscal.web.dto.RequestSummaryDTO;

import java.time.OffsetDateTime;
import java.util.*;

/**
 * Mapper manual entre DTOs y entidades.
 * - Convierte CreateRequestDTO -> RequestEntity
 * - Convierte RequestEntity -> RequestResponseDTO / RequestSummaryDTO
 *
 * Nota: la entidad RequestEntity tiene el campo `documentos` como JSONB (List / Map).
 * Aquí representamos cada documento como Map<String,Object> para serializar a jsonb.
 */
public final class RequestMapper {

    private RequestMapper() {}

    /**
     * Convierte CreateRequestDTO a RequestEntity.
     * No toca createdAt/updatedAt (dejamos que @PrePersist los establezca),
     * pero generamos el UUID aquí para tener el id disponible inmediatamente.
     */
    public static RequestEntity toEntity(CreateRequestDTO dto) {
        if (dto == null) return null;

        RequestEntity e = RequestEntity.builder().build();

        // id: generar ahora para poder usarlo si se necesita (history, respuesta inmediata, etc.)
        e.setId(UUID.randomUUID());

        e.setNombreCompleto(dto.getNombreCompleto());
        e.setCorreo(dto.getCorreo());
        e.setTelefono(dto.getTelefono());
        e.setRfc(dto.getRfc());
        e.setTipoContribuyente(dto.getTipoContribuyente());

        if (dto.getServicioId() != null) {
            e.setServicioId(dto.getServicioId());
        } else {
            e.setServicioId(null);
        }
        e.setServicioText(dto.getServicioText());
        e.setDescripcionBreve(dto.getDescripcionBreve());

        // documentos -> convertir DTOs a List<Map<String,Object>> para JSONB
        e.setDocumentos(documentItemsToJsonb(dto.getDocumentos()));

        e.setSource("chatbot_web");
        e.setStatus("nuevo");      // valor por defecto
        e.setPrioridad("media");   // valor inicial; el servicio o scoring puede cambiarlo
        e.setScore(0);

        // timestamps: si quieres inicializarlos aquí podrías, pero @PrePersist también lo hace.
        e.setCreatedAt(OffsetDateTime.now());
        e.setUpdatedAt(OffsetDateTime.now());

        return e;
    }

    /**
     * Convierte RequestEntity a RequestResponseDTO (respuesta mínima).
     */
    public static RequestResponseDTO toResponse(RequestEntity entity) {
        if (entity == null) return null;
        RequestResponseDTO dto = new RequestResponseDTO();
        dto.setId(entity.getId());
        dto.setPrioridad(entity.getPrioridad());
        dto.setScore(entity.getScore());
        return dto;
    }

    /**
     * Convierte RequestEntity a RequestSummaryDTO (use para listados).
     */
    public static RequestSummaryDTO toSummary(RequestEntity entity) {
        if (entity == null) return null;
        RequestSummaryDTO s = new RequestSummaryDTO();
        s.setId(entity.getId());
        s.setNombreCompleto(entity.getNombreCompleto());
        s.setCorreo(entity.getCorreo());
        s.setServicioText(entity.getServicioText());
        s.setPrioridad(entity.getPrioridad());
        s.setStatus(entity.getStatus());
        s.setAssignedTo(entity.getAssignedTo());
        s.setCreatedAt(entity.getCreatedAt());
        return s;
    }

    /**
     * Convierte la lista de DocumentItemDTO a una estructura que podamos almacenar en JSONB.
     * Cada item será un Map con keys {"documentId","key","label","have","notes"}.
     */
    public static List<Object> documentItemsToJsonb(List<DocumentItemDTO> items) {
        if (items == null) return Collections.emptyList();
        List<Object> out = new ArrayList<>(items.size());
        for (DocumentItemDTO it : items) {
            if (it == null) continue;
            Map<String,Object> m = new LinkedHashMap<>();
            if (it.getDocumentId() != null) m.put("documentId", it.getDocumentId().toString());
            else m.put("documentId", null);
            m.put("key", it.getKey());
            m.put("label", it.getLabel());
            m.put("have", it.getHave() == null ? Boolean.FALSE : it.getHave());
            m.put("notes", it.getNotes());
            out.add(m);
        }
        return out;
    }

    /**
     * Convierte la estructura JSONB (List<Object> / List<Map<String,Object>>) que sale de la entidad
     * a una lista de DocumentItemDTO. Útil si quieres mostrar en la API el detalle de documentos.
     *
     * Nota: el campo en la entidad puede ser List<Map<String,Object>> o List<Object> dependiendo
     * de cómo JPA/Hibernate lo devuelva; aquí hacemos casteos defensivos.
     */
    @SuppressWarnings("unchecked")
    public static List<DocumentItemDTO> jsonbToDocumentItems(Object jsonb) {
        if (jsonb == null) return Collections.emptyList();
        List<DocumentItemDTO> out = new ArrayList<>();
        if (jsonb instanceof List) {
            List<?> raw = (List<?>) jsonb;
            for (Object o : raw) {
                if (o instanceof Map) {
                    Map<String,Object> m = (Map<String,Object>) o;
                    DocumentItemDTO dto = new DocumentItemDTO();
                    Object docId = m.get("documentId");
                    if (docId != null) {
                        try {
                            dto.setDocumentId(UUID.fromString(docId.toString()));
                        } catch (Exception ex) {
                            // ignore invalid uuid
                        }
                    }
                    dto.setKey(safeToString(m.get("key")));
                    dto.setLabel(safeToString(m.get("label")));
                    Object have = m.get("have");
                    dto.setHave(have == null ? Boolean.FALSE : Boolean.valueOf(have.toString()));
                    dto.setNotes(safeToString(m.get("notes")));
                    out.add(dto);
                }
            }
        }
        return out;
    }

    private static String safeToString(Object o) {
        return o == null ? null : o.toString();
    }
}