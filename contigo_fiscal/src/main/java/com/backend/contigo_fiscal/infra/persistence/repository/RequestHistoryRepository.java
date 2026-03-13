package com.backend.contigo_fiscal.infra.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.contigo_fiscal.infra.persistence.entity.RequestHistoryEntity;

public interface RequestHistoryRepository extends JpaRepository<RequestHistoryEntity, UUID> {

}
