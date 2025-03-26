package com.example.JPA_3Classes.Repository;

import com.example.JPA_3Classes.Entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
