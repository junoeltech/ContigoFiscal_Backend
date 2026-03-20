package com.backend.contigo_fiscal.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequestDTO {

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 250)
    private String nombreCompleto;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe ser un email válido")
    private String correo;

    @Size(max = 40)
    @JsonProperty("telefono")
    private String telefono;

    @Pattern(regexp = "[A-Z&Ñ]{3,4}[0-9]{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[A-Z0-9]{2}[0-9A]", 
         message = "Formato de RFC inválido")
    @Size(max = 20)
    @JsonProperty("rfc")
    private String rfc;

    @NotBlank(message = "El tipo de contribuyente es obligatorio")
    private String tipoContribuyente; 

    private UUID servicioId;
}