package com.example.JPA_3Classes.Controller;

import com.example.JPA_3Classes.Entity.Curso;
import com.example.JPA_3Classes.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping("/adicionar")
    public ResponseEntity<Curso> create(@RequestBody Curso curso) {
        Curso cursoBd = cursoRepository.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cursoBd);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Curso>> getAll() {
        List<Curso> cursoList = cursoRepository.findAll();

        if (cursoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(cursoList);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Curso> getById(@PathVariable Long id) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();

            return ResponseEntity.ok(curso);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Curso cursoAtt) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            curso.setNome(cursoAtt.getNome());
            curso.setNumeroSala(cursoAtt.getNumeroSala());
            curso.setProfessor(cursoAtt.getProfessor());

            return ResponseEntity.ok(cursoRepository.save(curso));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            cursoRepository.delete(curso);

            return ResponseEntity.ok("Curso deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado.");
        }
    }
}
