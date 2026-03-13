package com.backend.contigo_fiscal.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO para listados en el dashboard (resumen).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestSummaryDTO {
    private UUID id;
    private String nombreCompleto;
    private String correo;
    private String servicioText;
    private String prioridad;
    private String status;
    private UUID assignedTo;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdAt;
}