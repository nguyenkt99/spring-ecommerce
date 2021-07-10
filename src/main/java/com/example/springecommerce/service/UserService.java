package com.example.springecommerce.service;

import com.example.springecommerce.dto.UserDTO;

import java.util.List;

public interface UserService {
    public UserDTO save(UserDTO userDTO);
    public List<UserDTO> getUsers();
    public UserDTO getUser(UserDTO userDTO);
}
