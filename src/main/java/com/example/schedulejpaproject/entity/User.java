package com.example.schedulejpaproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

/**
 * 사용자 정보를 나타내는 엔티티 클래스입니다.
 * 기본 생성 시간 및 수정 시간을 상속받습니다.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseTimeEntity {

    /**
     * 사용자 ID (기본 키).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 사용자 이름 (고유값).
     */
    @Column(nullable = false,unique = true)
    private  String username;

    /**
     * 사용자 이메일 (고유값).
     */
    @Column(nullable = false, unique = true)
    private  String email;

    /**
     * 사용자 비밀번호.
     */
    @Column(nullable = false)
    private String password;
}
