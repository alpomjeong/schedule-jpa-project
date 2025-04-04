package com.example.schedulejpaproject.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 사용자 정보를 응답할 때 사용하는 DTO 클래스입니다.
 */
@Setter
@Getter
public class UserResponseDto {

    /**
     * 사용자 ID.
     */
    private Long id;

    /**
     * 사용자 이름.
     */
    private String username;

    /**
     * 사용자 이메일.
     */
    private String email;

    /**
     * 사용자 계정 생성 시각.
     */
    private LocalDateTime created_at;

    /**
     * 사용자 계정 마지막 수정 시각.
     */
    private LocalDateTime updated_at;
}
