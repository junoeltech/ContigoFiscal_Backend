package com.backend.contigo_fiscal.infra.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.contigo_fiscal.infra.persistence.entity.DocumentEntity;

public interface DocumentRepository extends JpaRepository<DocumentEntity,UUID> {

}
