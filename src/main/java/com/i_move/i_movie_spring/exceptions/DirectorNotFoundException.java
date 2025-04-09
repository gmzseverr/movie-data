package com.i_move.i_movie_spring.exceptions;

public class DirectorNotFoundException extends RuntimeException {
    public DirectorNotFoundException(String message) {
        super((message));
    }
}
