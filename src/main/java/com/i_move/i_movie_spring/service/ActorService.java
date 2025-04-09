package com.i_move.i_movie_spring.service;

import com.i_move.i_movie_spring.dto.ActorDTO;
import com.i_move.i_movie_spring.dto.ActorMoviesDTO;
import com.i_move.i_movie_spring.dto.MovieResponse;

import java.util.List;


public interface ActorService {

    ActorDTO createActor(ActorDTO actorDTO);
    ActorDTO findById(Long id);
    ActorDTO findByName(String name);
    List<ActorDTO> findAllActors();
    ActorMoviesDTO getActorWithMovies(Long id);
    List<MovieResponse> getMoviesByActorId(Long id);
    void deleteActor(Long id);
}
