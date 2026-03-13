package com.backend.contigo_fiscal.infra.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.contigo_fiscal.infra.persistence.entity.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, UUID> {
    Optional<ServiceEntity> findByCode(String code);
}
