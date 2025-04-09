package com.i_move.i_movie_spring.exceptions;

import org.springframework.http.HttpStatus;

public class UserExceptions extends RuntimeException {
    private HttpStatus httpStatus;

}
