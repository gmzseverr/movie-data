package com.i_move.i_movie_spring.controller;


import com.i_move.i_movie_spring.dto.DirectorDTO;
import com.i_move.i_movie_spring.exceptions.DirectorNotFoundException;
import com.i_move.i_movie_spring.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RequestMapping("/director")
@RestController
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public ResponseEntity<List<DirectorDTO>> getAllDirectors() {
        List<DirectorDTO> directors = directorService.findAll();
        return new ResponseEntity<>(directors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> getDirectorById(@PathVariable Long id) {
        DirectorDTO director = directorService.findById(id);
        if (director == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found with id: " + id);
        }
        return new ResponseEntity<>(director, HttpStatus.OK);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<DirectorDTO> getDirectorByName(@PathVariable String name) {
        try {
            DirectorDTO director = directorService.findByName(name);
            return new ResponseEntity<>(director, HttpStatus.OK);
        } catch (DirectorNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<DirectorDTO> createDirector(@RequestBody DirectorDTO directorDTO) {
        DirectorDTO savedDirector = directorService.save(directorDTO);
        return new ResponseEntity<>(savedDirector, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DirectorDTO> deleteDirector(@PathVariable Long id) {
        DirectorDTO deletedDirector = directorService.delete(id);
        if (deletedDirector == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found with id: " + id);
        }
        return new ResponseEntity<>(deletedDirector, HttpStatus.OK);
    }
}