package com.backend.contigo_fiscal.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Representa un item en la checklist que el usuario responde en el chatbot.
 * Ej: { documentId, key, label, have, notes }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentItemDTO {
    /**
     * Id del documento (catalogo 'documentos') — opcional si el frontend envía solo key.
     */
    private UUID documentId;

    /**
     * clave corta del documento (ej. 'cfdi')
     */
    @NotBlank
    @Size(max = 60)
    private String key;

    /**
     * etiqueta visible (ej. 'CFDI(s)')
     */
    @NotBlank
    @Size(max = 120)
    private String label;

    /**
     * true si el usuario declara tenerlo
     */
    private Boolean have = Boolean.FALSE;

    /**
     * notas libres si el usuario escribe algo
     */
    @Size(max = 500)
    private String notes;
}