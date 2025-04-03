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

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

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

    @Override
    public void deleteSchedule(Long id, Long userId) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        if (!schedule.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }

        scheduleRepository.delete(schedule);
    }

    @Override
    public List<ScheduleResponseDto> getSchedulesByUserId(Long userId) {
        return scheduleRepository.findByUserId(userId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


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
