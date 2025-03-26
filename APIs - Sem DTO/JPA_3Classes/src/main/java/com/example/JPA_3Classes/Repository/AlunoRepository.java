package com.example.JPA_3Classes.Repository;

import com.example.JPA_3Classes.Entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
