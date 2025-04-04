package com.example.schedulejpaproject.repository;

import com.example.schedulejpaproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 일정(Schedule) 엔티티에 대한 데이터 접근을 처리하는 JPA 리포지토리입니다.
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /**
     * 특정 사용자 ID로 등록된 일정 목록을 조회합니다.
     *
     * @param userId 사용자 ID
     * @return 일정 엔티티 리스트
     */
    List<Schedule> findByUserId(Long userId);
}
