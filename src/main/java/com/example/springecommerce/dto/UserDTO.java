package com.example.springecommerce.dto;

import com.example.springecommerce.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private UserDetailDTO userDetail;
    private List<OrderDTO> orders = new ArrayList<>();
    private List<RoleDTO> roles = new ArrayList<>();

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.userDetail = new UserDetailDTO(user.getUserDetail());
        this.roles = user.getRoles().stream().map(RoleDTO::new)
                        .collect(Collectors.toList());
    }

    public User toEntity() {
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        //user.setUserDetail(userDetailDTO.toEntity());
        return user;
    }
}
