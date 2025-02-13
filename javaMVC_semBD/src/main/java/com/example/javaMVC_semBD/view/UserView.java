package com.example.javaMVC_semBD.view;

import com.example.javaMVC_semBD.controller.UserController;
import com.example.javaMVC_semBD.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserView {

    // Instanciando controller
    UserController userController = new UserController();

    @GetMapping
    public List<User> getAll() { // Buscando todos users
        return userController.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) { // Buscando por id
        return userController.getById(id);
    }

    @PostMapping
    public boolean insert(@RequestBody User user) { // Criando user
        return userController.insert(user);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable Long id) { // Update user
        return userController.update(id, user);
    }
}
