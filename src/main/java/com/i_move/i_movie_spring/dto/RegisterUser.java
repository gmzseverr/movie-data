package com.i_move.i_movie_spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterUser(
        @NotBlank String fullName,
        @Email @NotBlank String email,
        @Size(min = 5) String password,
        @Pattern(regexp = "^(USER|ADMIN)$") String role
) {
}
