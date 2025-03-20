package com.example.JPA_3Classes.Controller;

import com.example.JPA_3Classes.Entity.Aluno;
import com.example.JPA_3Classes.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping("/alunoAdd")
    public ResponseEntity<Aluno> create(@RequestBody Aluno aluno) {
        Aluno alunoBd = alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alunoBd);
    }

    @GetMapping("/alunoFind")
    public ResponseEntity<List<Aluno>> getAll() {
        List<Aluno> alunoList = alunoRepository.findAll();
        if (alunoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(alunoList);
    }
}
