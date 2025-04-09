package com.i_move.i_movie_spring.dto;



import com.i_move.i_movie_spring.entity.Actor;
import com.i_move.i_movie_spring.entity.Director;
import com.i_move.i_movie_spring.entity.Genre;
import lombok.*;

import java.util.List;
import java.util.Set;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponse {
    private Long id;
    private String title;
    private int year;
    private String description;
    private int duration;
    private double imdbRating;
    private String posterUrl;
    private String trailerUrl;
    private String wallpaperUrl;
    private String logoUrl;
    private Director director;
    private List<Actor> actors;
    private List<Genre> genres;
}

