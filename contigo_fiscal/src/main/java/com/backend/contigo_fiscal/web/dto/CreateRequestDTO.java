package com.backend.contigo_fiscal.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequestDTO {

    @NotBlank(message = "nombreCompleto es obligatorio")
    @Size(max = 250)
    private String nombreCompleto;

    @NotBlank(message = "correo es obligatorio")
    @Email(message = "correo debe ser un email válido")
    private String correo;

    @Size(max = 40)
    private String telefono;

    @Size(max = 20)
    private String rfc;

    @NotBlank(message = "tipoContribuyente es obligatorio")
    private String tipoContribuyente;

    private UUID servicioId;

    @Size(max = 250)
    private String servicioText;

    @Size(max = 2000)
    private String descripcionBreve;

    @Valid
    private List<DocumentItemDTO> documentos;

    private String preferenciaContacto;

    @NotNull(message = "consentimiento es obligatorio")
    private Boolean consentimiento;

    @NotNull(message = "consentimientoTs es obligatorio")
    @JsonFormat(shape = JsonFormat.Shape.STRING) // acepta ISO offset datetime
    private OffsetDateTime consentimientoTs;

    private String consentimientoVersion;
}