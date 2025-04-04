package com.example.schedulejpaproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 엔티티의 생성일자와 수정일자를 자동으로 관리하는 기반 클래스입니다.
 * 다른 엔티티 클래스에서 상속하여 사용할 수 있도록 MappedSuperclass로 설정되어 있습니다.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    /**
     * 생성 시각. 엔티티가 처음 저장될 때 자동으로 설정됩니다.
     */
    @CreatedDate
    @Column(name ="created_at",updatable = false, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 수정 시각. 엔티티가 변경될 때마다 자동으로 갱신됩니다.
     */
    @LastModifiedDate
    @Column(name ="updated_at",updatable = false, nullable = false)
    private LocalDateTime updatedAt;
}
