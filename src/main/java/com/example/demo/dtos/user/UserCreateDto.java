package com.example.demo.dtos.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserCreateDto {
    private String fullName;
    private String email;
    private String password;
}
