package com.project.uber.UberApp.dto;

import com.project.uber.UberApp.entities.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String name;
    private String email;

    private Set<Roles> roles;
}
