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


/**
 * 일정 관련 요청을 처리하는 REST 컨트롤러입니다.
 */
@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 새로운 일정을 생성합니다.
     *
     * @param requestDto 생성할 일정 정보
     * @param request    세션에서 사용자 ID를 가져오기 위한 요청 객체
     * @return 생성된 일정 정보
     */
    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody @Valid ScheduleRequestDto requestDto,
                                              HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");
        return scheduleService.saveSchedule(requestDto, userId);
    }

    /**
     * 로그인한 사용자의 모든 일정을 조회합니다.
     *
     * @param request 세션에서 사용자 ID를 가져오기 위한 요청 객체
     * @return 일정 목록
     */

    @GetMapping
    public List<ScheduleResponseDto> getAllSchedules(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");
        return scheduleService.getSchedulesByUserId(userId);
    }

    /**
     * 기존 일정을 수정합니다.
     *
     * @param id         수정할 일정 ID
     * @param requestDto 수정할 일정 정보
     * @param request    세션에서 사용자 ID를 가져오기 위한 요청 객체
     * @return 수정된 일정 정보
     */
    @PutMapping("/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id,
                                              @RequestBody @Valid ScheduleRequestDto requestDto,
                                              HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");
        return scheduleService.updateSchedule(id, requestDto, userId);
    }

    /**
     * 일정을 삭제합니다.
     *
     * @param id      삭제할 일정 ID
     * @param request 세션에서 사용자 ID를 가져오기 위한 요청 객체
     * @return 응답 본문 없이 204 No Content 반환
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id,
                                               HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");
        scheduleService.deleteSchedule(id, userId);
        return ResponseEntity.noContent().build();
    }

}

