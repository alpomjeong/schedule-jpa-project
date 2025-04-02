package com.example.schedulejpaproject.controller;

import com.example.schedulejpaproject.dto.user.UserRequestDto;
import com.example.schedulejpaproject.dto.user.UserResponseDto;
import com.example.schedulejpaproject.service.User.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDto createUser(@RequestBody @Valid UserRequestDto requestDto){
        return userService.createUser(requestDto);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
