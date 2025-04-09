package com.i_move.i_movie_spring.service;

import com.i_move.i_movie_spring.dto.MovieRequest;
import com.i_move.i_movie_spring.dto.MovieResponse;
import com.i_move.i_movie_spring.entity.Actor;
import com.i_move.i_movie_spring.entity.Genre;
import com.i_move.i_movie_spring.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
   MovieResponse createMovie(MovieRequest movieRequest);
   MovieResponse updateMovie(Long id, MovieRequest request);
   List<MovieResponse> getAllMovies();
   MovieResponse getMovieById(Long id);
   void deleteMovie(Long id);
   List<MovieResponse> findByGenres(Genre genre);
   Optional<Movie> findByTitleAndDirectorNameAndActorsAndGenres(String title, String directorName, List<Actor> actors, List<Genre> genres);
   boolean isAlreadySaved(String title, String directorName, List<Actor> actors, List<Genre> genres);

}
