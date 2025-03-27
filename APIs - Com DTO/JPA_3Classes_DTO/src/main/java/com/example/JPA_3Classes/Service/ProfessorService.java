package com.example.JPA_3Classes.Service;

import com.example.JPA_3Classes.DTO.ProfessorDTO;
import com.example.JPA_3Classes.Entity.Professor;
import com.example.JPA_3Classes.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    // Converte Professor para ProfessorDTO
    public ProfessorDTO toDTO(Professor professor) {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(professor.getIdProfessor());
        professorDTO.setNome(professor.getNome());
        professorDTO.setCpf(professor.getCpf());

        return professorDTO;
    }

    // Converte professorDTO em professor
    public Professor fromDTO(ProfessorDTO professorDTO) {
        Professor professor = new Professor();
        professor.setNome(professorDTO.getNome());
        professor.setCpf(professorDTO.getCpf());

        return professor;
    }

    public List<Professor> getAll() {
        return professorRepository.findAll();
    }

    // Buscar por ID
    public Optional<ProfessorDTO> getById(Long id) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);

        if (optionalProfessor.isPresent()) {
            return Optional.of(this.toDTO(optionalProfessor.get()));
        } else {
            return Optional.empty();
        }
    }

    public ProfessorDTO save(ProfessorDTO professorDTO) {
        Professor professor = this.fromDTO(professorDTO);
        Professor professorBd = professorRepository.save(professor);

        return this.toDTO(professorBd);
    }

    public Optional<ProfessorDTO> updateProfessor(Long id, ProfessorDTO professorDTO) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);

        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();

            professor.setNome(professorDTO.getNome());
            professor.setCpf(professorDTO.getCpf());

            Professor professorAtt = professorRepository.save(professor);

            return Optional.of(this.toDTO(professorAtt));
        } else {
            return Optional.empty();
        }
    }

    public boolean delete(Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
