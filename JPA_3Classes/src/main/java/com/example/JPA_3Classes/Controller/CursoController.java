package com.example.JPA_3Classes.Controller;

import com.example.JPA_3Classes.Entity.Curso;
import com.example.JPA_3Classes.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
