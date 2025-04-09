package com.i_move.i_movie_spring.controller;

import com.i_move.i_movie_spring.dto.ActorMoviesDTO;
import com.i_move.i_movie_spring.dto.GenreMoviesDTO;
import com.i_move.i_movie_spring.dto.MovieRequest;
import com.i_move.i_movie_spring.dto.MovieResponse;
import com.i_move.i_movie_spring.entity.Movie;
import com.i_move.i_movie_spring.exceptions.MovieException;
import com.i_move.i_movie_spring.service.ActorService;
import com.i_move.i_movie_spring.service.GenreService;
import com.i_move.i_movie_spring.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final GenreService genreService;
    private final ActorService actorService;

    // Tüm filmleri listele
    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<MovieResponse> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    // Movie ID ile arama
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
        try {
            MovieResponse movie = movieService.getMovieById(id);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("genres/{id}")
    public ResponseEntity<GenreMoviesDTO> getGenreWithMovies(@PathVariable Long id) {
        GenreMoviesDTO genreWithMovies = genreService.getGenreWithMovies(id);
        return ResponseEntity.ok(genreWithMovies);
    }

    @GetMapping("actor/{id}")
    public ResponseEntity<ActorMoviesDTO> getActorWithMovies(@PathVariable Long id) {
        ActorMoviesDTO actorWithMovies = actorService.getActorWithMovies(id);
        return ResponseEntity.ok(actorWithMovies);
    }


    @PostMapping("admin/add")
    public ResponseEntity<MovieResponse> createMovie(@RequestBody MovieRequest movieRequest) {

        MovieResponse movieResponse = movieService.createMovie(movieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponse);
    }

    @PutMapping("admin/update/{id}")
    public ResponseEntity<MovieResponse> updateMovie(
            @PathVariable Long id,
            @RequestBody MovieRequest movieRequest) {
        try {
            MovieResponse updatedMovie = movieService.updateMovie(id, movieRequest);
            return ResponseEntity.ok(updatedMovie);
        } catch (MovieException e) {
            return ResponseEntity.notFound().build();
        }
    }





    @DeleteMapping("admin/remove/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        try {
            movieService.deleteMovie(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
