package com.example.springecommerce.dto;

import com.example.springecommerce.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Integer id;
    private String name;

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }

    public Role toEntity() {
        Role role = new Role();
        if(this.id != null) {
            role.setId(this.id);
        }
        role.setName(this.name);
        return role;
    }
}
