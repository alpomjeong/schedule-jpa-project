package com.example.schedulejpaproject.service.Schedule;

import com.example.schedulejpaproject.dto.schedule.ScheduleRequestDto;
import com.example.schedulejpaproject.dto.schedule.ScheduleResponseDto;

import java.util.List;

/**
 * 일정(Schedule) 관련 비즈니스 로직을 정의하는 서비스 인터페이스입니다.
 */
public interface ScheduleService {

    /**
     * 일정을 생성합니다.
     *
     * @param requestDto 생성할 일정 정보
     * @param userId     일정을 등록할 사용자 ID
     * @return 생성된 일정 정보
     */
    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto, Long userId);

    /**
     * 특정 사용자의 모든 일정을 조회합니다.
     *
     * @param userId 사용자 ID
     * @return 일정 목록
     */
    List<ScheduleResponseDto> getSchedulesByUserId(Long userId);

    /**
     * 일정을 수정합니다.
     *
     * @param id         수정할 일정 ID
     * @param requestDto 수정할 내용이 담긴 DTO
     * @param userId     일정 소유자(수정 권한자)의 사용자 ID
     * @return 수정된 일정 정보
     */
    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto, Long userId);

    /**
     * 일정을 삭제합니다.
     *
     * @param id     삭제할 일정 ID
     * @param userId 일정 소유자(삭제 권한자)의 사용자 ID
     */
    void deleteSchedule(Long id, Long userId);

}
