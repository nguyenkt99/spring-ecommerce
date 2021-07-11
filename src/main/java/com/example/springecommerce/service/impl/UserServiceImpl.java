package com.example.springecommerce.service.impl;

import com.example.springecommerce.dto.UserDTO;
import com.example.springecommerce.entity.User;
import com.example.springecommerce.entity.UserDetail;
import com.example.springecommerce.exception.NotFoundException;
import com.example.springecommerce.repository.UserRepository;
import com.example.springecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

//    @Autowired
//    UserDetailRepository userDetailRepository;

    @Override
    public UserDTO save(UserDTO userDTO) {
        boolean isExists = userRepository.existsByUsername(userDTO.getUsername());
        if(!isExists) {
            UserDetail userDetail = userDTO.getUserDetail().toEntity();
            User user = userDTO.toEntity();
            user.setUserDetail(userDetail);
            userDetail.setUser(user);
            User savedUser = userRepository.save(user);
            return new UserDTO(savedUser);
        }
        return null;
    }

    @Override
    public UserDTO getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not exists"));
        return new UserDTO(user);
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(UserDTO::new)
                .collect(Collectors.toList());
    }
}
