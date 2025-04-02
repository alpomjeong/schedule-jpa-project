package com.example.schedulejpaproject.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserResponseDto {

    private Long id;
    private String username;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
