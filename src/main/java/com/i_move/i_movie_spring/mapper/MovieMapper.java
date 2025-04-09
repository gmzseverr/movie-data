package com.i_move.i_movie_spring.mapper;

import com.i_move.i_movie_spring.dto.MovieRequest;
import com.i_move.i_movie_spring.dto.MovieResponse;
import com.i_move.i_movie_spring.entity.Actor;
import com.i_move.i_movie_spring.entity.Director;
import com.i_move.i_movie_spring.entity.Genre;
import com.i_move.i_movie_spring.entity.Movie;
import com.i_move.i_movie_spring.repository.ActorRepository;
import com.i_move.i_movie_spring.repository.DirectorRepository;
import com.i_move.i_movie_spring.repository.GenreRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class MovieMapper {
    private final ModelMapper modelMapper;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;

    public Movie movieRequestDTOtoMovie(MovieRequest movieRequestDTO) {
        if (movieRequestDTO == null) {
            throw new IllegalArgumentException("MovieRequest cannot be null");
        }

        Movie movie = modelMapper.map(movieRequestDTO, Movie.class);

        // Director işlemleri
        String directorName = movieRequestDTO.getDirectorName();
        if (directorName == null || directorName.isBlank()) {
            throw new IllegalArgumentException("Director name cannot be null or empty");
        }

        Director director = directorRepository.findByName(directorName)
                .orElseGet(() -> directorRepository.save(new Director(directorName)));
        movie.setDirector(director);

        // Actor işlemleri
        List<Actor> actors = movieRequestDTO.getActorNames().stream()
                .map(actorName -> {
                    if (actorName == null || actorName.isBlank()) {
                        throw new IllegalArgumentException("Actor name cannot be null or empty");
                    }
                    return actorRepository.findByName(actorName)
                            .orElseGet(() -> actorRepository.save(new Actor(actorName)));
                })
                .collect(Collectors.toList());
        movie.setActors(actors);

        // Genre işlemleri
        List<Genre> genres = movieRequestDTO.getGenreNames().stream()
                .map(genreName -> {
                    if (genreName == null || genreName.isBlank()) {
                        throw new IllegalArgumentException("Genre name cannot be null or empty");
                    }
                    return genreRepository.findByName(genreName)
                            .orElseGet(() -> genreRepository.save(new Genre(genreName)));
                })
                .collect(Collectors.toList());
        movie.setGenres(genres);

        return movie;
    }

    public MovieResponse movieToMovieResponseDTO(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }
        return modelMapper.map(movie, MovieResponse.class);
    }
}