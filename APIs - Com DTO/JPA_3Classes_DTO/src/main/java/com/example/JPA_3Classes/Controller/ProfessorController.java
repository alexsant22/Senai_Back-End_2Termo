package com.example.JPA_3Classes.Controller;

import com.example.JPA_3Classes.DTO.ProfessorDTO;
import com.example.JPA_3Classes.Entity.Professor;
import com.example.JPA_3Classes.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping("/adicionar")
    public ResponseEntity<Professor> created(@RequestBody ProfessorDTO professorDTO) {
        Professor professorBd = professorService.save(professorDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(professorBd);
    }
}
