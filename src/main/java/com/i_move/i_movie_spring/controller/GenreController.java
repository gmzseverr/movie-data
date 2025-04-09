package com.i_move.i_movie_spring.controller;


import com.i_move.i_movie_spring.dto.GenreDTO;
import com.i_move.i_movie_spring.dto.GenreMoviesDTO;
import com.i_move.i_movie_spring.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreDTO genreDTO) {
        GenreDTO createdGenre = genreService.createGenre(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id) {
        return genreService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/by-name/{name}")
    public ResponseEntity<GenreDTO> getGenreByName(@PathVariable String name) {
        return genreService.findByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        List<GenreDTO> genres = genreService.findAllGenres();
        return ResponseEntity.ok(genres);
    }


    @GetMapping("/{id}/movies")
    public ResponseEntity<GenreMoviesDTO> getGenreWithMovies(@PathVariable Long id) {
        GenreMoviesDTO genreWithMovies = genreService.getGenreWithMovies(id);
        return ResponseEntity.ok(genreWithMovies);
    }


}