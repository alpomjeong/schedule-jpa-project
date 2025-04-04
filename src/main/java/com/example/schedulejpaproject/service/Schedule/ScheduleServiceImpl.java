package com.example.schedulejpaproject.service.Schedule;

import com.example.schedulejpaproject.dto.schedule.ScheduleRequestDto;
import com.example.schedulejpaproject.dto.schedule.ScheduleResponseDto;
import com.example.schedulejpaproject.entity.Schedule;
import com.example.schedulejpaproject.entity.User;
import com.example.schedulejpaproject.repository.ScheduleRepository;
import com.example.schedulejpaproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 일정(Schedule) 관련 비즈니스 로직을 구현한 서비스 클래스입니다.
 */
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    /**
     * 새로운 일정을 저장합니다.
     *
     * @param requestDto 일정 생성 요청 데이터
     * @param userId     일정 생성자 ID
     * @return 생성된 일정 정보
     */
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Schedule schedule = new Schedule();
        schedule.setTitle(requestDto.getTitle());
        schedule.setContent(requestDto.getContent());
        schedule.setUser(user);

        Schedule saved = scheduleRepository.save(schedule);

        ScheduleResponseDto responseDto = new ScheduleResponseDto();
        responseDto.setId(saved.getId());
        responseDto.setTitle(saved.getTitle());
        responseDto.setContent(saved.getContent());
        responseDto.setUserId(user.getId());
        responseDto.setUsername(user.getUsername());
        responseDto.setCreated_at(saved.getCreatedAt());
        responseDto.setUpdated_at(saved.getUpdatedAt());
        return responseDto;
    }

    /**
     * 기존 일정을 수정합니다.
     *
     * @param id         수정할 일정 ID
     * @param requestDto 수정할 데이터
     * @param userId     요청한 사용자 ID
     * @return 수정된 일정 정보
     */
    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto, Long userId) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        if (!schedule.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        schedule.setTitle(requestDto.getTitle());
        schedule.setContent(requestDto.getContent());

        Schedule updated = scheduleRepository.save(schedule);
        return toDto(updated);
    }

    /**
     * 일정을 삭제합니다.
     *
     * @param id     삭제할 일정 ID
     * @param userId 요청한 사용자 ID
     */
    @Override
    public void deleteSchedule(Long id, Long userId) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        if (!schedule.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }

        scheduleRepository.delete(schedule);
    }

    /**
     * 사용자 ID로 등록된 일정 목록을 조회합니다.
     *
     * @param userId 사용자 ID
     * @return 일정 응답 DTO 리스트
     */
    @Override
    public List<ScheduleResponseDto> getSchedulesByUserId(Long userId) {
        return scheduleRepository.findByUserId(userId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    /**
     * Schedule 엔티티를 ScheduleResponseDto로 변환합니다.
     *
     * @param schedule 변환할 일정 엔티티
     * @return 변환된 일정 응답 DTO
     */
    private ScheduleResponseDto toDto(Schedule schedule) {
        ScheduleResponseDto dto = new ScheduleResponseDto();
        dto.setId(schedule.getId());
        dto.setTitle(schedule.getTitle());
        dto.setContent(schedule.getContent());
        dto.setUserId(schedule.getUser().getId());
        dto.setUsername(schedule.getUser().getUsername());
        dto.setCreated_at(schedule.getCreatedAt());
        dto.setUpdated_at(schedule.getUpdatedAt());
        return dto;
    }
}
