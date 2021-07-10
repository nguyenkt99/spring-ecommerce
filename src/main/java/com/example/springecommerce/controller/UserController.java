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

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PostMapping("/login")
    UserDTO login(@RequestBody UserDTO userDTO) {
        UserDTO acc = userService.getUser(userDTO);
        if(acc != null) {
            return acc;
        }
        return null;
    }
}
