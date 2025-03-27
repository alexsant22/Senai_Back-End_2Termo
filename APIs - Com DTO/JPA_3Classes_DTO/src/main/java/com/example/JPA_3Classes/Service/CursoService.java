package com.example.JPA_3Classes.Service;

import com.example.JPA_3Classes.DTO.CursoDTORequest;
import com.example.JPA_3Classes.DTO.CursoDTOResponse;
import com.example.JPA_3Classes.Entity.Curso;
import com.example.JPA_3Classes.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    // Converte de CursoDTORequest para Curso
    public Curso fromDTO(CursoDTORequest cursoDTORequest) {
        Curso curso = new Curso();

        curso.setNome(cursoDTORequest.getNome());
        curso.setNumeroSala(cursoDTORequest.getNumeroSala());
        curso.setProfessor(cursoDTORequest.getProfessor());

        return curso;
    }

    // Converte de Curso para CursoDTOResponse
    public CursoDTOResponse toDTO(Curso curso) {
        CursoDTOResponse cursoDTOResponse = new CursoDTOResponse();

        cursoDTOResponse.setId(curso.getIdCurso());
        cursoDTOResponse.setNome(curso.getNome());
        cursoDTOResponse.setNumeroSala(curso.getNumeroSala());
        cursoDTOResponse.setProfessor(curso.getProfessor());
        cursoDTOResponse.setAlunos(curso.getAlunoList());

        return cursoDTOResponse;
    }

    public List<Curso> getAll() {
        return cursoRepository.findAll();
    }

    public Optional<CursoDTOResponse> getById(Long id) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isPresent()) {
            return Optional.of(this.toDTO(cursoOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    public CursoDTOResponse saveDto(CursoDTORequest cursoDTORequest) {
        Curso curso = this.fromDTO(cursoDTORequest);
        Curso cursoBd = cursoRepository.save(curso);

        return this.toDTO(cursoBd);
    }

    public Optional<CursoDTOResponse> updateCurso(Long id, CursoDTORequest cursoDTORequest) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);

        if(cursoOptional.isPresent()){
            Curso curso = cursoOptional.get();
            curso.setNome(cursoDTORequest.getNome());
            curso.setNumeroSala(cursoDTORequest.getNumeroSala());
            curso.setProfessor(cursoDTORequest.getProfessor());

            Curso cursoUpdate = cursoRepository.save(curso);

            return Optional.of(this.toDTO(cursoUpdate));
        }else {
            return Optional.empty();
        }
    }
}
