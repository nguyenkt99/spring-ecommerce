package com.example.springecommerce.controller;

import com.example.springecommerce.dto.UserDTO;
import com.example.springecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<UserDTO> getAccounts() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

}
