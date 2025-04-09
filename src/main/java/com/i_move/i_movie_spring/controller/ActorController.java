package com.i_move.i_movie_spring.controller;



import com.i_move.i_movie_spring.dto.ActorDTO;
import com.i_move.i_movie_spring.dto.ActorMoviesDTO;
import com.i_move.i_movie_spring.dto.MovieResponse;
import com.i_move.i_movie_spring.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @PostMapping
    public ResponseEntity<ActorDTO> createActor(@RequestBody ActorDTO actorDTO) {
        ActorDTO createdActor = actorService.createActor(actorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> getActorById(@PathVariable Long id) {
        ActorDTO actor = actorService.findById(id);
        return ResponseEntity.ok(actor);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ActorDTO> getActorByName(@PathVariable String name) {
        ActorDTO actor = actorService.findByName(name);
        return ResponseEntity.ok(actor);
    }

    @GetMapping
    public ResponseEntity<List<ActorDTO>> getAllActors() {
        List<ActorDTO> actors = actorService.findAllActors();
        return ResponseEntity.ok(actors);
    }



    @GetMapping("/{id}/movies")
    public ResponseEntity<List<MovieResponse>> getMoviesByActor(@PathVariable Long id) {
        List<MovieResponse> movies = actorService.getMoviesByActorId(id);
        return ResponseEntity.ok(movies);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
        return ResponseEntity.noContent().build();
    }
}
