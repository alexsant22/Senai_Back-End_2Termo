package com.example.JPA_3Classes.Controller;

import com.example.JPA_3Classes.Entity.Professor;
import com.example.JPA_3Classes.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping("/professorAdd")
    public ResponseEntity<Professor> create(@RequestBody Professor professor) {
        Professor professorBd = professorRepository.save(professor);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(professorBd);
    }

    @GetMapping("/professoresFind")
    public ResponseEntity<List<Professor>> getAll() {
        List<Professor> professorList = professorRepository.findAll();

        if (professorList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(professorList);
    }
}
