package com.example.schedulejpaproject.dto.schedule;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private String title;
    private String content;
    private Long userId;
}
