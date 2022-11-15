package com.example.redis_test.controller;

import com.example.redis_test.model.User;
import com.example.redis_test.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/get/{id}")
    @Cacheable(value = "user", key = "#id")
    public User getUserByID(@PathVariable("id") long id){
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            return userData.get();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @PostMapping("/create")
    public ResponseEntity<User> addUser(User user) {
        try {
            user = userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    @Cacheable(value = "persons")
    public List<User> usersList() {
        return userRepository.findAll();
    }
}
