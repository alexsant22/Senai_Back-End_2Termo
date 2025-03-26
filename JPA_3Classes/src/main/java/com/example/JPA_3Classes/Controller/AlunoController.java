package com.example.JPA_3Classes.Controller;

import com.example.JPA_3Classes.Entity.Aluno;
import com.example.JPA_3Classes.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping("/adicionar")
    public ResponseEntity<Aluno> create(@RequestBody Aluno aluno) {
        Aluno alunoBd = alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alunoBd);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Aluno>> getAll() {
        List<Aluno> alunoList = alunoRepository.findAll();
        if (alunoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(alunoList);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Aluno> getById(@PathVariable Long id) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);

        if (optionalAluno.isPresent()) {
            Aluno aluno = optionalAluno.get();
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Aluno alunoAtt) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);

        if (optionalAluno.isPresent()) {
            Aluno aluno = optionalAluno.get();
            aluno.setNome(alunoAtt.getNome());
            aluno.setCpf(alunoAtt.getCpf());
            aluno.setCurso(alunoAtt.getCurso());

            return ResponseEntity.ok(alunoRepository.save(aluno));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);

        if (optionalAluno.isPresent()) {
            Aluno aluno = optionalAluno.get();
            alunoRepository.delete(aluno);

            return ResponseEntity.ok("Aluno deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado.");
        }
    }
}
