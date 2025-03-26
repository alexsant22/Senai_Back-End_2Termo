package com.example.JPA_3Classes.Service;

import com.example.JPA_3Classes.DTO.ProfessorDTO;
import com.example.JPA_3Classes.Entity.Professor;
import com.example.JPA_3Classes.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public Professor fromDTO(ProfessorDTO professorDTO) {
        Professor professor = new Professor();
        professor.setNome(professorDTO.getNome());
        professor.setCpf(professorDTO.getCpf());

        return professor;
    }

    public Professor save(ProfessorDTO professorDTO) {
        Professor professor = this.fromDTO(professorDTO);
        Professor professorBd = professorRepository.save(professor);

        return professorBd;
    }
}
