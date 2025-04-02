package com.example.schedulejpaproject.service.User;

import com.example.schedulejpaproject.dto.schedule.ScheduleResponseDto;
import com.example.schedulejpaproject.dto.user.UserRequestDto;
import com.example.schedulejpaproject.dto.user.UserResponseDto;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {
        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setEmail(requestDto.getEmail());

        User saved = userRepository.save(user);

        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(saved.getId());
        responseDto.setUsername(saved.getUsername());
        responseDto.setEmail(saved.getEmail());
        responseDto.setCreated_at(saved.getCreatedAt());
        responseDto.setUpdated_at(saved.getUpdatedAt());

        return responseDto;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
        return toDto(user);
    }

    private UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCreated_at(user.getCreatedAt());
        dto.setUpdated_at(user.getUpdatedAt());
        return dto;
    }
}
