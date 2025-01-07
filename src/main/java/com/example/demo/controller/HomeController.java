package com.example.demo.controller;

import com.example.demo.entities.User;
import com.example.demo.models.RegisterUserDto;
import com.example.demo.response.HomeResponse;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/home")
public class HomeController {


    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationService authenticationService;


    @GetMapping("/data")
    public ResponseEntity<HomeResponse> helloWorld() {
        HomeResponse hr = new HomeResponse();
        hr.setData("hello world");
        return new ResponseEntity<HomeResponse>(hr, HttpStatus.OK);
    }

    @GetMapping("/rate-limited")
    public ResponseEntity<HomeResponse> register() {
        HomeResponse hr = new HomeResponse();
        hr.setData("hello world");
        return new ResponseEntity<HomeResponse>(hr, HttpStatus.OK);
    }

}
