package com.example.schedulejpaproject.service.User;

import com.example.schedulejpaproject.dto.schedule.ScheduleResponseDto;
import com.example.schedulejpaproject.dto.user.UserLoginRequestDto;
import com.example.schedulejpaproject.dto.user.UserRequestDto;
import com.example.schedulejpaproject.dto.user.UserResponseDto;
import com.example.schedulejpaproject.dto.user.UserSignupRequestDto;
import com.example.schedulejpaproject.entity.Schedule;
import com.example.schedulejpaproject.entity.User;
import com.example.schedulejpaproject.repository.ScheduleRepository;
import com.example.schedulejpaproject.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 사용자(User) 관련 비즈니스 로직을 구현한 서비스 클래스입니다.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    /**
     * 사용자 회원가입을 처리합니다.
     *
     * @param dto 회원가입 요청 데이터
     * @return 생성된 사용자 정보
     */
    @Override
    public UserResponseDto signup(UserSignupRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // 비밀번호 암호화는 추후 적용

        User saved = userRepository.save(user);

        UserResponseDto res = new UserResponseDto();
        res.setId(saved.getId());
        res.setUsername(saved.getUsername());
        res.setEmail(saved.getEmail());
        res.setCreated_at(saved.getCreatedAt());
        res.setUpdated_at(saved.getUpdatedAt());
        return res;
    }

    /**
     * 사용자 로그인을 처리하고, 세션에 사용자 ID를 저장합니다.
     *
     * @param dto     로그인 요청 데이터
     * @param request HttpServletRequest (세션 저장용)
     */
    @Override
    public void login(UserLoginRequestDto dto, HttpServletRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(dto.getEmail());

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("이메일이 존재하지 않습니다.");
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user.getId());

    }

    /**
     * 전체 사용자 목록을 조회합니다.
     *
     * @return 사용자 응답 DTO 리스트
     */
    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 사용자 ID로 사용자 정보를 조회합니다.
     *
     * @param id 사용자 ID
     * @return 사용자 응답 DTO
     */
    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
        return toDto(user);
    }

    /**
     * 사용자 정보를 수정합니다.
     *
     * @param id         사용자 ID
     * @param requestDto 수정할 사용자 정보
     * @return 수정된 사용자 응답 DTO
     */
    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        user.setUsername(requestDto.getUsername());
        user.setEmail(requestDto.getEmail());

        User updated = userRepository.save(user);
        return toDto(updated);
    }

    /**
     * 사용자 계정을 삭제합니다.
     *
     * @param id 사용자 ID
     */
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        userRepository.delete(user);
    }

    /**
     * User 엔티티를 UserResponseDto로 변환합니다.
     *
     * @param user 변환할 사용자 엔티티
     * @return 사용자 응답 DTO
     */
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
