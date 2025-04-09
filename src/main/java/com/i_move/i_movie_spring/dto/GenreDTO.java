package com.i_move.i_movie_spring.dto;

import com.i_move.i_movie_spring.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDTO {

    private Long id;
    private String name;

}