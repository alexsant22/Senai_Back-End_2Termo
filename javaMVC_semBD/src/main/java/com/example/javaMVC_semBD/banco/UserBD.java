package com.example.javaMVC_semBD.banco;

import com.example.javaMVC_semBD.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserBD {

    public List<User> users; // Lista para usuários (simulando BD)

    public UserBD() { // Construtor
        this.users = new ArrayList<>();
    }

    // Buscar todos usuários
    public List<User> findAll(){
        return new ArrayList<>(users);
    }

    // Busca apenas um usuário
    public User getById(Long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Inserir usuário
    public boolean inset(User user) {
        users.add(user);
        return true;
    }

    // Update usuário
    public boolean upadate(Long id, User user) {
        User userBd = users.stream()
                .filter(userFilter -> userFilter.getId() == id)
                .findFirst()
                .orElse(null);

        if (userBd == null) {
            return false;
        }

        userBd.setNome(user.getNome());
        userBd.setSobrenome(user.getSobrenome());

        return true;
    }
}
