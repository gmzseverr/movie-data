package com.i_move.i_movie_spring.dto;

import com.i_move.i_movie_spring.entity.Actor;
import com.i_move.i_movie_spring.entity.Director;
import com.i_move.i_movie_spring.entity.Genre;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class MovieRequest {


    private String title;
    private int year;
    private String description;
    private int duration;
    private double imdbRating;
    private String posterUrl;
    private String trailerUrl;
    private String wallpaperUrl;
    private String logoUrl;
    private String directorName;
    private List<String> actorNames;
    private List<String> genreNames;
}
