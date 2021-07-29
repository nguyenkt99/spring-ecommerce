package com.example.springecommerce.service.impl;

import com.example.springecommerce.dto.UserDetailDTO;
import com.example.springecommerce.entity.User;
import com.example.springecommerce.entity.UserDetail;
import com.example.springecommerce.exception.NotFoundException;
import com.example.springecommerce.repository.UserDetailRepository;
import com.example.springecommerce.repository.UserRepository;
import com.example.springecommerce.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetailDTO saveUserDetail(UserDetailDTO userDetailDTO) {
        User user = userRepository.getById(userDetailDTO.getUserId());
        UserDetail userDetail = null;
        userDetail = userDetailRepository.findById(userDetailDTO.getUserId()).orElse(null);
        if(userDetail != null) {
            userDetail.setName(userDetailDTO.getName());
            userDetail.setPhone(userDetailDTO.getPhone());
            userDetail.setAddress(userDetailDTO.getAddress());
        } else {
            userDetail = userDetailDTO.toEntity();
            userDetail.setUser(user);
        }
        UserDetail savedUserDetail = userDetailRepository.save(userDetail);
        return new UserDetailDTO(savedUserDetail);
    }

    @Override
    public UserDetailDTO getUserDetail(long id) {
        UserDetail userDetail =  userDetailRepository.findById(id).orElseThrow(() ->
                new NotFoundException("UserDetail not exists"));
        return new UserDetailDTO(userDetail);
    }
}
