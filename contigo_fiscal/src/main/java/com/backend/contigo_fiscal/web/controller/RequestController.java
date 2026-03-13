package com.backend.contigo_fiscal.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.contigo_fiscal.application.service.RequestService;
import com.backend.contigo_fiscal.infra.persistence.entity.RequestEntity;
import com.backend.contigo_fiscal.web.dto.CreateRequestDTO;
import com.backend.contigo_fiscal.web.dto.RequestResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RequestController {
    private final RequestService requestService;

    @PostMapping
    public ResponseEntity<RequestResponseDTO> create(@Valid @RequestBody CreateRequestDTO dto) {
        RequestEntity saved = requestService.create(dto);
        RequestResponseDTO resp = new RequestResponseDTO();
        resp.setId(saved.getId());
        resp.setPrioridad(saved.getPrioridad());
        return ResponseEntity.ok(resp);
    }
}