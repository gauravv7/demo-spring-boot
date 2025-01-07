package com.example.demo.models;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterUserDto {

    private String email;

    private String password;

    private String fullName;
}
