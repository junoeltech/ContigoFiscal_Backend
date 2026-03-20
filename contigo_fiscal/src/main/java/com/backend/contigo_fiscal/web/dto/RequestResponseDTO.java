package com.backend.contigo_fiscal.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestResponseDTO {
    private UUID id;
    private String prioridad; 
    private Integer score;
}