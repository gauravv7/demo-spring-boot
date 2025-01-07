package com.example.demo.models;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginUserDto {
    private String email;

    private String password;

}
