package com.example.JPA_3Classes.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;
    private String nome;
    private int numeroSala;

    // Relacionamento de Professor com Curso
    @OneToOne() // 1 para 1
    // Criando coluna 'idProfessor' com referencia ao atributo 'idProfessor'
    @JoinColumn(name = "idProfessor", referencedColumnName = "idProfessor")
    @JsonManagedReference
    private Professor professor; // Obj Professor

    // Relacionamento com Aluno
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Aluno> alunoList;
}
