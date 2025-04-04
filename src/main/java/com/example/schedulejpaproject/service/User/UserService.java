package com.example.schedulejpaproject.service.User;

import com.example.schedulejpaproject.dto.user.UserLoginRequestDto;
import com.example.schedulejpaproject.dto.user.UserRequestDto;
import com.example.schedulejpaproject.dto.user.UserResponseDto;
import com.example.schedulejpaproject.dto.user.UserSignupRequestDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * 사용자(User) 관련 비즈니스 로직을 정의하는 서비스 인터페이스입니다.
 */
public interface UserService {

    /**
     * 사용자 회원가입을 처리합니다.
     *
     * @param dto 회원가입 요청 데이터
     * @return 생성된 사용자 정보
     */
    UserResponseDto signup(UserSignupRequestDto dto);

    /**
     * 사용자 로그인을 처리합니다. 세션에 사용자 ID를 저장합니다.
     *
     * @param dto     로그인 요청 데이터
     * @param request HttpServletRequest (세션 저장용)
     */
    void login(UserLoginRequestDto dto, HttpServletRequest request);

    /**
     * 전체 사용자 목록을 조회합니다.
     *
     * @return 사용자 정보 리스트
     */
    List<UserResponseDto> getAllUsers();

    /**
     * 사용자 ID로 사용자 정보를 조회합니다.
     *
     * @param id 사용자 ID
     * @return 사용자 정보
     */
    UserResponseDto getUserById(Long id);


    /**
     * 사용자 정보를 수정합니다.
     *
     * @param id         사용자 ID
     * @param requestDto 수정할 정보
     * @return 수정된 사용자 정보
     */
    UserResponseDto updateUser(Long id, UserRequestDto requestDto);

    /**
     * 사용자 계정을 삭제합니다.
     *
     * @param id 사용자 ID
     */
    void deleteUser(Long id);

}
