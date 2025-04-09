package com.i_move.i_movie_spring.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank @Email String email, @NotBlank @Size(min=5) String password) {
}
