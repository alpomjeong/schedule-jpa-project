package com.example.schedulejpaproject.dto.schedule;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleResponseDto {

    private Long id;
    private String title;
    private String content;
    private Long userId;
    private String username;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
