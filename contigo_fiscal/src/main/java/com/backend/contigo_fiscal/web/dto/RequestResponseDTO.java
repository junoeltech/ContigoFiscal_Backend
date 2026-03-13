package com.backend.contigo_fiscal.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Respuesta mínima tras crear una solicitud.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestResponseDTO {
    private UUID id;
    private String prioridad; // 'alta'|'media'|'baja'
    private Integer score;
}