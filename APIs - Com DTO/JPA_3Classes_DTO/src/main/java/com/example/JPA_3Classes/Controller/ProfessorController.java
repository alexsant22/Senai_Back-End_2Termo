package com.example.JPA_3Classes.Controller;

import com.example.JPA_3Classes.DTO.ProfessorDTO;
import com.example.JPA_3Classes.Entity.Professor;
import com.example.JPA_3Classes.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping("/adicionar")
    public ResponseEntity<ProfessorDTO> created(@RequestBody ProfessorDTO professorDTO) {
        ProfessorDTO professorBd = professorService.save(professorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorBd);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Professor>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.getAll());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ProfessorDTO> getById(@PathVariable Long id) {
        Optional<ProfessorDTO> professorDTO = professorService.getById(id);

        if (!professorDTO.isEmpty()) {
            return ResponseEntity.ok(professorDTO.get());
        } else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProfessorDTO> update(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO) {
        Optional<ProfessorDTO> professorDTOOptional = professorService.updateProfessor(id, professorDTO);

        if (professorDTOOptional.isPresent()) {
            return ResponseEntity.ok(professorDTOOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (professorService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
