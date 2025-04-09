package com.i_move.i_movie_spring.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EmailAlreadyExistsException extends RuntimeException{

    private HttpStatus status;

    public EmailAlreadyExistsException(String email) {
        super(String.format("A user with this email already exists %s", email));

        this.status = status;
    }
}