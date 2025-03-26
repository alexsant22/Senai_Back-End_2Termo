package com.example.aula1Spring;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private List<User> users = new ArrayList<>(); // Stored users

    @GetMapping
    public List<User> getUser() { // List users
        return users;
    }

    @GetMapping("/{id}")
    @Operation(description = "Retorna o usuário com base no id")
    public User getUserById(@PathVariable int id) { // Search users (id)
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) { // Update users
        User updateUser = users.stream()
                .filter(usuario -> usuario.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        // Info updates
        updateUser.setNome(user.getNome());
        updateUser.setSobrenome(user.getSobrenome());

        return updateUser; // Saved user update
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) { // Create user
        users.add(newUser); // Add user in array
        return newUser; // Return user created
    }
}
