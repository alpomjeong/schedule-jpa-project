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

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserResponseDto signup(@RequestBody @Valid UserSignupRequestDto dto) {
        return userService.signup(dto);
    }

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

    @GetMapping("/me")
    public UserResponseDto getUserById(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");
        return userService.getUserById(userId);
    }

    @PutMapping("/me")
    public UserResponseDto updateUser(HttpServletRequest request,
                                      @RequestBody @Valid UserRequestDto dto) {
        Long userId = (Long) request.getSession().getAttribute("user");
        return userService.updateUser(userId, dto);
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
