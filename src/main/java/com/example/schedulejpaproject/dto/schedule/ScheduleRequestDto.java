package com.example.schedulejpaproject.dto.schedule;

import lombok.Getter;

/**
 * 일정 생성 또는 수정을 위한 요청 DTO 클래스입니다.
 */
@Getter
public class ScheduleRequestDto {

    /**
     * 일정 제목.
     */
    private String title;

    /**
     * 일정 내용.
     */
    private String content;

    /**
     * 일정 작성자 ID (User 엔티티의 ID).
     */
    private Long userId;
}
