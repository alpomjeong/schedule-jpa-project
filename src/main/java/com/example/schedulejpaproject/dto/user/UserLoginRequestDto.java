package com.example.schedulejpaproject.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 사용자 로그인 요청 시 사용되는 DTO 클래스입니다.
 */
@Getter
@Setter
public class UserLoginRequestDto {

    /**
     * 사용자 이메일. 이메일 형식이며, 비어 있을 수 없습니다.
     */
    @Email
    @NotBlank
    private String email;

    /**
     * 사용자 비밀번호. 비어 있을 수 없습니다.
     */
    @NotBlank
    private String password;
}
