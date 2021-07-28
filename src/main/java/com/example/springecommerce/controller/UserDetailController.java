package com.example.springecommerce.controller;

import com.example.springecommerce.dto.UserDetailDTO;
import com.example.springecommerce.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserDetailController {

    @Autowired
    UserDetailService userDetailService;

    @PostMapping("/userDetails")
    UserDetailDTO addUserDetail(@RequestBody UserDetailDTO userDetailDTO) {
        return userDetailService.saveUserDetail(userDetailDTO);
    }

    @GetMapping("/userDetails/{id}")
    UserDetailDTO getUserDetail(@PathVariable long id) {
        return userDetailService.getUserDetail(id);
    }

}
