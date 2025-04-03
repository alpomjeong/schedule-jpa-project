package com.example.schedulejpaproject.service.Schedule;

import com.example.schedulejpaproject.dto.schedule.ScheduleRequestDto;
import com.example.schedulejpaproject.dto.schedule.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto, Long userId);
    List<ScheduleResponseDto> getSchedulesByUserId(Long userId);
    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto, Long userId);
    void deleteSchedule(Long id, Long userId);

}
