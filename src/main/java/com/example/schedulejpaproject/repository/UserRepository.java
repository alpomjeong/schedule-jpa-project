package com.example.schedulejpaproject.repository;

import com.example.schedulejpaproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 사용자(User) 엔티티에 대한 데이터 접근을 처리하는 JPA 리포지토리입니다.
 */
public interface UserRepository extends JpaRepository<User,Long> {


    /**
     * 이메일을 기준으로 사용자를 조회합니다.
     *
     * @param email 사용자 이메일
     * @return 해당 이메일을 가진 사용자(Optional)
     */
    Optional<User> findByEmail(String email);
}
