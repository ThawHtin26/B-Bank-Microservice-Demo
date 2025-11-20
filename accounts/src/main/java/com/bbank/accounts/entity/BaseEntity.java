package com.bbank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Base Entity for all entities
 * @Author Thaw Htin Aung
 */
@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {

    @CreationTimestamp
    @Column(updatable = false,
            name = "created_at")
    private LocalDateTime createdAt;

    @Column(updatable = false,
            name = "created_by")
    private String createdBy;

    @Column(insertable = false,
            name = "updated_at")
    private LocalDateTime updatedAt;

    @UpdateTimestamp
    @Column(insertable = false,
            name = "updated_by")
    private String updatedBy;
}
