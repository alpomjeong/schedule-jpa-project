package com.example.schedulejpaproject.controller;

import com.example.schedulejpaproject.dto.user.UserLoginRequestDto;
import com.example.schedulejpaproject.dto.user.UserRequestDto;
import com.example.schedulejpaproject.dto.user.UserResponseDto;
import com.example.schedulejpaproject.dto.user.UserSignupRequestDto;
import com.example.schedulejpaproject.service.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 사용자 관련 요청을 처리하는 REST 컨트롤러입니다.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 사용자 회원가입을 처리합니다.
     *
     * @param dto 회원가입 요청 데이터
     * @return 생성된 사용자 정보
     */
    @PostMapping("/signup")
    public UserResponseDto signup(@RequestBody @Valid UserSignupRequestDto dto) {
        return userService.signup(dto);
    }

    /**
     * 사용자 로그인을 처리합니다.
     * 세션에 사용자 ID를 저장합니다.
     *
     * @param dto     로그인 요청 데이터
     * @param request 세션을 위한 HttpServletRequest 객체
     * @return 로그인 성공 시 200 OK, 실패 시 401 UNAUTHORIZED 응답
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginRequestDto dto,
                                   HttpServletRequest request) {
        try {
            userService.login(dto, request);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "code", "C001",
                    "message","로그인이 필요합니다",
                    "status", "UNAUTHORIZED"
            ));
        }
    }

    /**
     * 현재 로그인한 사용자 정보를 반환합니다.
     *
     * @param request 세션을 위한 HttpServletRequest 객체
     * @return 사용자 정보
     */
    @GetMapping("/me")
    public UserResponseDto getUserById(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");
        return userService.getUserById(userId);
    }

    /**
     * 현재 로그인한 사용자 정보를 수정합니다.
     *
     * @param request 세션을 위한 HttpServletRequest 객체
     * @param dto     수정할 사용자 정보
     * @return 수정된 사용자 정보
     */

    @PutMapping("/me")
    public UserResponseDto updateUser(HttpServletRequest request,
                                      @RequestBody @Valid UserRequestDto dto) {
        Long userId = (Long) request.getSession().getAttribute("user");
        return userService.updateUser(userId, dto);
    }

    /**
     * 현재 로그인한 사용자 계정을 삭제합니다.
     *
     * @param request 세션을 위한 HttpServletRequest 객체
     * @return 204 No Content 응답
     */
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    /**
     * 전체 사용자 목록을 조회합니다.
     *
     * @return 사용자 목록
     */
    @GetMapping("/all")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
