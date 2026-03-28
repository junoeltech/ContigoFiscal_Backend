package com.backend.contigo_fiscal.web.controller;

import com.backend.contigo_fiscal.application.service.RequestService;
import com.backend.contigo_fiscal.infra.mapper.RequestMapper;
import com.backend.contigo_fiscal.infra.persistence.entity.RequestEntity;
import com.backend.contigo_fiscal.web.dto.CreateRequestDTO;
import com.backend.contigo_fiscal.web.dto.RequestResponseDTO;
import com.backend.contigo_fiscal.web.dto.RequestSummaryDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RequestController {

    private final RequestService requestService;

  

    @PostMapping("/crear")
    public ResponseEntity<RequestResponseDTO> create(@Valid @RequestBody CreateRequestDTO dto) {
        RequestEntity saved = requestService.create(dto);
        return ResponseEntity.ok(RequestMapper.toResponse(saved));
    }


    @GetMapping("/consultar")
public ResponseEntity<List<RequestSummaryDTO>> getAll() {
    // El Service ya hace el trabajo de mapeo y de buscar los nombres de los servicios
    List<RequestSummaryDTO> list = requestService.findAll(); 
    return ResponseEntity.ok(list);
}

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable UUID id, 
            @RequestParam String status) {
        requestService.updateStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        requestService.delete(id); 
        return ResponseEntity.noContent().build();
}
}