package com.example.springecommerce.dto;

import com.example.springecommerce.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
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
    private Set<String> roles = new HashSet<>();

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
//        this.userDetail = new UserDetailDTO(user.getUserDetail());
        this.roles = user.getRoles().stream().map(role -> role.getName().toString()).collect(Collectors.toSet());
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
