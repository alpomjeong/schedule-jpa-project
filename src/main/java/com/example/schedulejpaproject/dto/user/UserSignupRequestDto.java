package com.example.schedulejpaproject.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 사용자 회원가입 요청 시 사용되는 DTO 클래스입니다.
 */
@Getter
public class UserSignupRequestDto {

    /**
     * 사용자 이름. 비어 있을 수 없습니다.
     */
    @NotBlank
    private String username;

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
