package com.example.schedulejpaproject.dto.user;


import lombok.Getter;

/**
 * 사용자 관련 요청에 사용되는 DTO 클래스입니다.
 * 주로 사용자 등록 등에서 사용됩니다.
 */
@Getter
public class UserRequestDto {

    /**
     * 사용자 이름.
     */
    private String username;

    /**
     * 사용자 이메일.
     */
    private String email;
}
