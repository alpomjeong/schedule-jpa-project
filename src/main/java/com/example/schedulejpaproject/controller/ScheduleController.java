package com.example.schedulejpaproject.controller;


import com.example.schedulejpaproject.dto.schedule.ScheduleRequestDto;
import com.example.schedulejpaproject.dto.schedule.ScheduleResponseDto;
import com.example.schedulejpaproject.service.Schedule.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleResponseDto createSchedule (@RequestBody @Valid ScheduleRequestDto requestDto){
        return scheduleService.saveSchedule(requestDto);
    }

    @GetMapping
    public List<ScheduleResponseDto> getAllSchedules(
            @RequestParam(value = "userId",required = false)Long userId){
        if(userId != null){
            return scheduleService.getSchedulesByUserId(userId);
        }
        return scheduleService.findAllSchedules();
    }


}

