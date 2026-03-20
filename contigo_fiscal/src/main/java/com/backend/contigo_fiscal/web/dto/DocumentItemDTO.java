package com.backend.contigo_fiscal.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentItemDTO {
   
    private UUID documentId;

   
    @NotBlank
    @Size(max = 60)
    private String key;

   
    @NotBlank
    @Size(max = 120)
    private String label;

    
    private Boolean have = Boolean.FALSE;

    @Size(max = 500)
    private String notes;
}