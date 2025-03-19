package com.example.ExercJPA.Controller;

import com.example.ExercJPA.Entity.User;
import com.example.ExercJPA.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
