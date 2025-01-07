package com.example.demo.controller;

import com.example.demo.response.HomeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/home")
public class HomeController {


    @GetMapping("/data")
    public ResponseEntity<HomeResponse> helloWorld() {
        HomeResponse hr = new HomeResponse();
        hr.setData("hello world");
        return new ResponseEntity<HomeResponse>(hr, HttpStatus.OK);
    }

}
