package com.example.demo.models;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponse {

    private int status;
    private String title;
    private String message;
    private String details;

    public ErrorResponse(int status, String title, String message) {
        this.status = status;
        this.title = title;
        this.message = message;
    }

}
