package com.example.schedulejpaproject.repository;

import com.example.schedulejpaproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
}
