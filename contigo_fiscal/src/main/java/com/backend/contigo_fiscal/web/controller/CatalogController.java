package com.backend.contigo_fiscal.web.controller;

import com.backend.contigo_fiscal.application.service.CatalogService;
import com.backend.contigo_fiscal.infra.persistence.entity.DocumentEntity;
import com.backend.contigo_fiscal.infra.persistence.entity.ServiceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/catalogs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("/services")
    public ResponseEntity<List<ServiceEntity>> getServices() {
        return ResponseEntity.ok(catalogService.getAllServices());
    }
    
    @GetMapping("/services/{serviceId}/documents")
    public ResponseEntity<List<DocumentEntity>> getDocuments(@PathVariable UUID serviceId) {
        List<DocumentEntity> documents = catalogService.getRequiredDocuments(serviceId);
        return ResponseEntity.ok(documents);
    }
}