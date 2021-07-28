package com.example.springecommerce.service;

import com.example.springecommerce.dto.UserDetailDTO;

public interface UserDetailService {
    UserDetailDTO saveUserDetail(UserDetailDTO userDetailDTO);
    UserDetailDTO getUserDetail(long id);

}
