package com.i_move.i_movie_spring.service;

import com.i_move.i_movie_spring.dto.MovieRequest;
import com.i_move.i_movie_spring.dto.MovieResponse;
import com.i_move.i_movie_spring.entity.Actor;
import com.i_move.i_movie_spring.entity.Genre;
import com.i_move.i_movie_spring.entity.Movie;
import com.i_move.i_movie_spring.exceptions.MovieException;
import com.i_move.i_movie_spring.mapper.MovieMapper;
import com.i_move.i_movie_spring.repository.ActorRepository;
import com.i_move.i_movie_spring.repository.DirectorRepository;
import com.i_move.i_movie_spring.repository.GenreRepository;
import com.i_move.i_movie_spring.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;
    private final DirectorRepository directorRepository;

    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper, ActorRepository actorRepository, GenreRepository genreRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.actorRepository = actorRepository;
        this.genreRepository = genreRepository;
        this.directorRepository = directorRepository;
    }

    @Override
    public MovieResponse createMovie(MovieRequest movieRequest) {

        Movie movie = movieMapper.movieRequestDTOtoMovie(movieRequest);


        List<Actor> actors = movie.getActors();
        List<Genre> genres = movie.getGenres();
        String directorName = movie.getDirector().getName();
        String movieName = movie.getTitle();

        if (isAlreadySaved(movieName, directorName, actors, genres)) {
            throw new MovieException("Bu film zaten kayıtlı: " + movieName);
        }

        // Kaydet ve response'a dönüştür
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.movieToMovieResponseDTO(savedMovie);
    }

    @Override
    public MovieResponse updateMovie(Long id, MovieRequest movieRequest) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieException("Movie not found with id: " + id));

        // Entity'yi güncelle
        Movie updatedMovie = movieMapper.movieRequestDTOtoMovie(movieRequest);
        updatedMovie.setId(existingMovie.getId()); // ID'yi koru

        Movie savedMovie = movieRepository.save(updatedMovie);
        return movieMapper.movieToMovieResponseDTO(savedMovie);
    }





    @Override
    public List<MovieResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movieMapper::movieToMovieResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MovieResponse getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        return movieMapper.movieToMovieResponseDTO(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found with id: " + id);
        }
        movieRepository.deleteById(id);
    }

    @Override
    public List<MovieResponse> findByGenres(Genre genre) {
        Set<Movie> movies = movieRepository.findByGenres(genre);
        return movies.stream()
                .map(movieMapper::movieToMovieResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> findByTitleAndDirectorNameAndActorsAndGenres(String title, String directorName, List<Actor> actors, List<Genre> genres) {
        return movieRepository.findByTitleAndDirector_NameAndActorsInAndGenresIn(title, directorName, actors, genres);
    }

    @Override
    public boolean isAlreadySaved(String title, String directorName, List<Actor> actors, List<Genre> genres) {
        return findByTitleAndDirectorNameAndActorsAndGenres(title, directorName, actors, genres).isPresent();
    }
}
