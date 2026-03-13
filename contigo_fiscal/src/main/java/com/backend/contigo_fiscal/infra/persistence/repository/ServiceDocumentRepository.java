package com.backend.contigo_fiscal.infra.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.contigo_fiscal.infra.persistence.entity.ServiceDocumentEntity;

public interface ServiceDocumentRepository extends JpaRepository<ServiceDocumentEntity, UUID> {
    List<ServiceDocumentEntity> findByServicioId(UUID servicioId);

}
