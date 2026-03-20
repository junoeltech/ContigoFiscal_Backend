package com.backend.contigo_fiscal.application.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.backend.contigo_fiscal.infra.persistence.entity.DocumentEntity;
import com.backend.contigo_fiscal.infra.persistence.entity.ServiceDocumentEntity;
import com.backend.contigo_fiscal.infra.persistence.entity.ServiceEntity;
import com.backend.contigo_fiscal.infra.persistence.repository.ServiceDocumentRepository;
import com.backend.contigo_fiscal.infra.persistence.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final ServiceRepository serviceRepository;
    private final ServiceDocumentRepository serviceDocumentRepository;

    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    public List<DocumentEntity> getRequiredDocuments(UUID serviceId) {
        return serviceDocumentRepository.findByServicioId(serviceId)
                .stream()
                .map(ServiceDocumentEntity::getDocumento)
                .collect(Collectors.toList());
    }
}
