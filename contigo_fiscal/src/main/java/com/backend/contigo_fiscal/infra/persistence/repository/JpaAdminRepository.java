package com.backend.contigo_fiscal.infra.persistence.repository;

import com.backend.contigo_fiscal.infra.persistence.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface JpaAdminRepository extends JpaRepository<AdminEntity, UUID> {
    Optional<AdminEntity> findByUsername(String username);
}