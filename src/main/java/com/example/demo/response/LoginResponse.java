package com.example.demo.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponse {

    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }

}
