package com.example.JPA_3Classes.DTO;

import com.example.JPA_3Classes.Entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTORequest {
    // Atributos
    private String nome;
    private int numeroSala;
    private Professor professor;
}
