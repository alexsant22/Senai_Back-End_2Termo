package com.example.JPA_3Classes.DTO;

import com.example.JPA_3Classes.Entity.Aluno;
import com.example.JPA_3Classes.Entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTOResponse {
    // Atributos
    private Long id;
    private String nome;
    private int numeroSala;
    private Professor professor;
    private List<Aluno> alunos;
}
