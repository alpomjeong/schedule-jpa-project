package com.example.schedulejpaproject.dto.schedule;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 일정 정보를 응답할 때 사용하는 DTO 클래스입니다.
 */
@Getter
@Setter
public class ScheduleResponseDto {

    /**
     * 일정 ID.
     */
    private Long id;

    /**
     * 일정 제목.
     */
    private String title;

    /**
     * 일정 내용.
     */
    private String content;

    /**
     * 작성자 ID.
     */
    private Long userId;

    /**
     * 작성자 이름.
     */
    private String username;

    /**
     * 일정 생성 시각.
     */
    private LocalDateTime created_at;

    /**
     * 일정 마지막 수정 시각.
     */
    private LocalDateTime updated_at;
}
