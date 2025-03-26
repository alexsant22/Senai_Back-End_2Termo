package com.example.ExercJPA.Repository;

import com.example.ExercJPA.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByNomeAndCargo (String nome, String cargo);
}
