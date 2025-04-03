package com.example.schedulejpaproject.controller;


import com.example.schedulejpaproject.dto.schedule.ScheduleRequestDto;
import com.example.schedulejpaproject.dto.schedule.ScheduleResponseDto;
import com.example.schedulejpaproject.service.Schedule.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody @Valid ScheduleRequestDto requestDto,
                                              HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");
        return scheduleService.saveSchedule(requestDto, userId);
    }

    @GetMapping
    public List<ScheduleResponseDto> getAllSchedules(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");
        return scheduleService.getSchedulesByUserId(userId);
    }

    @PutMapping("/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id,
                                              @RequestBody @Valid ScheduleRequestDto requestDto,
                                              HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");
        return scheduleService.updateSchedule(id, requestDto, userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id,
                                               HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");
        scheduleService.deleteSchedule(id, userId);
        return ResponseEntity.noContent().build();
    }

}

