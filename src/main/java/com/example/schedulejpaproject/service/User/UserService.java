package com.example.schedulejpaproject.service.User;


import com.example.schedulejpaproject.dto.user.UserRequestDto;
import com.example.schedulejpaproject.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto requestDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long id);
    UserResponseDto updateUser(Long id, UserRequestDto requestDto);

}
