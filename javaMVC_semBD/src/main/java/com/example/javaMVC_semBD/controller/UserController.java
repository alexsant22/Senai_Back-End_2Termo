package com.example.javaMVC_semBD.controller;

import com.example.javaMVC_semBD.banco.UserBD;
import com.example.javaMVC_semBD.model.User;

import java.util.List;

public class UserController {

    UserBD repository = new UserBD();

    // Buscar todos
    public List<User> getAll(){
        return repository.findAll();
    }

    // Busca por id
    public User getById(Long id) {
        return repository.getById(id);
    }

    // Inserir user
    public boolean insert(User user) {
        return repository.inset(user);
    }

    // Update user
    public User update(Long id, User user) {
        boolean result = repository.upadate(id, user);

        if (result) { // Tratamento se de certo ou não
            return user;
        }

        return null;
    }
}
