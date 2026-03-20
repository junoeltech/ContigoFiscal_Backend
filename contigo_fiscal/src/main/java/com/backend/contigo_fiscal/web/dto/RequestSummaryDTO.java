package com.backend.contigo_fiscal.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestSummaryDTO {
    private UUID id;
    private String nombreCompleto;
    private String correo;
    private String telefono;
    private String rfc;
    private String tipoContribuyente; 
    private String status;
    private OffsetDateTime createdAt;
}