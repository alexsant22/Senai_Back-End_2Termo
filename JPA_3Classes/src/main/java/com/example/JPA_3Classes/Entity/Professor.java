package com.example.JPA_3Classes.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfessor;
    private String nome;
    private String cpf;

    // Relacionamento de tabelas
    @OneToOne(mappedBy = "professor")
    @JsonIgnore  // Ignora o campo 'curso' durante a serialização
    private Curso curso;
}
