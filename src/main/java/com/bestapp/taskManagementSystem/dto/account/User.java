package com.bestapp.taskManagementSystem.dto.account;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Set<Role> roles;
}
