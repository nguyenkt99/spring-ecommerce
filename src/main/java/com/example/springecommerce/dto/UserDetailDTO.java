package com.example.springecommerce.dto;

import com.example.springecommerce.entity.UserDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {
    //private Long id;
    private String name;
    private String address;
    private String phone;
    private Long userId;

    public UserDetailDTO(UserDetail userDetail) {
        //this.id = userDetail.getId();
        this.name = userDetail.getName();
        this.address = userDetail.getAddress();
        this.phone = userDetail.getPhone();
        this.userId = userDetail.getUser().getId();
    }

    public UserDetail toEntity() {
        UserDetail userDetail = new UserDetail();
        userDetail.setName(this.name);
        userDetail.setAddress(this.address);
        userDetail.setPhone(this.phone);
        return userDetail;
    }
}
