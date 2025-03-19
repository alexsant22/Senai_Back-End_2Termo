package com.example.ExercJPA.Controller;

import com.example.ExercJPA.Entity.User;
import com.example.ExercJPA.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User userBd = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userBd);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(users);
    }

    @GetMapping("/busca")
    public ResponseEntity<List<User>> getByNameAndCargo(
            @RequestParam(required = true) String nome,
            @RequestParam(required = true) String cargo
    ) {
        List<User> usersNameAndCargo = userRepository.findByNomeAndCargo(nome, cargo);

        if (usersNameAndCargo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return  ResponseEntity.status(HttpStatus.OK)
                .body(usersNameAndCargo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        Optional<User> userBd = userRepository.findById(id);

        if (userBd.isPresent()) {
            User user = userBd.get();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User>  updateById(@RequestBody User user, @PathVariable Long id) {
        Optional<User> userBd = userRepository.findById(id);

        if (userBd.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }

        User userAtt = userBd.get();
        userAtt.setNome(user.getNome());
        userAtt.setEmail(user.getEmail());
        userRepository.save(userAtt);

        return  ResponseEntity.status(HttpStatus.OK)
                .body(userAtt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Usuário deletado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado");
        }
    }
}
