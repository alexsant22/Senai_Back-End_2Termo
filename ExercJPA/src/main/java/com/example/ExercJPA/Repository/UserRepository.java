package com.example.ExercJPA.Repository;

import com.example.ExercJPA.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
