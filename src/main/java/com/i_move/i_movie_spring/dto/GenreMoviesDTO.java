package com.i_move.i_movie_spring.dto;



import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreMoviesDTO {
    private Long id;
    private String name;
    private List<MovieResponse> movies;
}