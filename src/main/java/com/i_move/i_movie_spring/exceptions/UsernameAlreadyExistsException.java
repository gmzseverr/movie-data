package com.i_move.i_movie_spring.exceptions;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(@NotBlank @Size(min = 3, max = 20) String username) {
        super(String.format("Username '%s' is already taken", username));
    }

    public UsernameAlreadyExistsException(String message, @NotBlank @Size(min = 3, max = 20) String username) {
        super(String.format("%s: %s", message, username));
    }
}