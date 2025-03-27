package com.example.JPA_3Classes.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    // Atributos
    private Long id;
    private String nome;
    private String cpf;
}
