package com.backend.contigo_fiscal.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
}