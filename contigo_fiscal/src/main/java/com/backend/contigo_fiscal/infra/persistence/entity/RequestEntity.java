package com.backend.contigo_fiscal.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(nullable = false)
    private String correo;

    private String telefono;

    private String rfc;

    @Column(name = "tipo_contribuyente", nullable = false)
    private String tipoContribuyente;

    @Column(name = "servicio_id", columnDefinition = "uuid")
    private UUID servicioId;

    @Column(name = "servicio_text")
    private String servicioText;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", name = "documentos")
    private List<Object> documentos;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "prioridad", nullable = false)
    private String prioridad;

    @Column(name = "descripcion_breve")
    private String descripcionBreve;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "assigned_to", columnDefinition = "uuid")
    private UUID assignedTo;

    @Column(name = "source")
    private String source;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (createdAt == null) createdAt = OffsetDateTime.now();
        if (updatedAt == null) updatedAt = OffsetDateTime.now();
        if (score == null) score = 0;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = OffsetDateTime.now();
    }
}