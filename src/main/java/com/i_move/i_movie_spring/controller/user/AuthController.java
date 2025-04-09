package com.i_move.i_movie_spring.controller.user;


import com.i_move.i_movie_spring.dto.LoginRequest;
import com.i_move.i_movie_spring.dto.RegisterUser;
import com.i_move.i_movie_spring.dto.UserResponseDTO;
import com.i_move.i_movie_spring.service.user.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationService authenticationService;



    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody RegisterUser registerUser){

        return authenticationService.register(
                registerUser.fullName(),
                registerUser.email(),
                registerUser.password());

    }

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody @Valid LoginRequest loginUser) {
        return authenticationService.login(
                loginUser.email(),
                loginUser.password());
    }
}
