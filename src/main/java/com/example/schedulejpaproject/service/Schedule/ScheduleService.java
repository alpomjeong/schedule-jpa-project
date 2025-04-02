package com.example.schedulejpaproject.service.Schedule;

import com.example.schedulejpaproject.dto.schedule.ScheduleRequestDto;
import com.example.schedulejpaproject.dto.schedule.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);
    List<ScheduleResponseDto> findAllSchedules();
    List<ScheduleResponseDto> getSchedulesByUserId(Long userId);

}
