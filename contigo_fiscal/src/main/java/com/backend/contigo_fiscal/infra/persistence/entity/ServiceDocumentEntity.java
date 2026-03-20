package com.backend.contigo_fiscal.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "servicios_documentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceDocumentEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicio_id", nullable = false)
    private ServiceEntity servicio;
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "documento_id", nullable = false)
    private DocumentEntity documento;

    @Column(nullable = false)
    private Boolean required;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (createdAt == null) createdAt = OffsetDateTime.now();
        if (required == null) required = true;
    }
}