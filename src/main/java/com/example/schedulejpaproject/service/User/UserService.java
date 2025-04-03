package com.example.schedulejpaproject.service.User;

import com.example.schedulejpaproject.dto.user.UserLoginRequestDto;
import com.example.schedulejpaproject.dto.user.UserRequestDto;
import com.example.schedulejpaproject.dto.user.UserResponseDto;
import com.example.schedulejpaproject.dto.user.UserSignupRequestDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {
    UserResponseDto signup(UserSignupRequestDto dto);
    void login(UserLoginRequestDto dto, HttpServletRequest request);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long id);
    UserResponseDto updateUser(Long id, UserRequestDto requestDto);
    void deleteUser(Long id);

}
