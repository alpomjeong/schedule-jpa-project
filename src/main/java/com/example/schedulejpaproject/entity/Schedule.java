package com.example.schedulejpaproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 일정(Schedule) 정보를 나타내는 엔티티 클래스입니다.
 * 사용자(User)와 다대일(N:1) 관계를 가집니다.
 * 생성 및 수정 시간은 BaseTimeEntity로부터 상속받습니다.
 */
@Entity
@Table(name = "todos")
@Getter
@Setter
@NoArgsConstructor
public class Schedule extends BaseTimeEntity {
    /**
     * 일정 ID (기본 키).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 일정 제목. 비어 있을 수 없습니다.
     */
    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    /**
     * 일정 내용 (선택 사항).
     */
    private String content;

    /**
     * 이 일정을 등록한 사용자.
     * User 엔티티와 다대일 관계를 가집니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
