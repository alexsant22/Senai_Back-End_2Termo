package com.example.JPA_3Classes.Repository;

import com.example.JPA_3Classes.Entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
