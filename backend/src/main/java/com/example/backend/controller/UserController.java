package com.example.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.UserDTO;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.User;
import com.example.backend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.createUser(
            user.getUsername(),
            user.getPassword(),
            user.getName(),
            user.getEmail()
        );
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        userService.login(username, password);
        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserDTO> getProfile(@PathVariable long id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }
}
