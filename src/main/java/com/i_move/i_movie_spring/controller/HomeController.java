package com.i_move.i_movie_spring.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/home")
@RestController
public class HomeController {


    @GetMapping
    public String home(){
        return "welcommmeeemmmmm";
    }
}
