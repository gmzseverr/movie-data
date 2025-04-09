package com.i_move.i_movie_spring.mapper;

import com.i_move.i_movie_spring.dto.ActorDTO;
import com.i_move.i_movie_spring.entity.Actor;
import com.i_move.i_movie_spring.entity.Movie;
import com.i_move.i_movie_spring.repository.MovieRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import com.i_move.i_movie_spring.dto.ActorDTO;
import com.i_move.i_movie_spring.dto.ActorMoviesDTO;
import com.i_move.i_movie_spring.entity.Actor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ActorMapper {

    private final ModelMapper modelMapper;
    private final MovieMapper movieMapper;

    // Actor -> ActorDTO
    public ActorDTO actorToActorDTO(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("Actor cannot be null");
        }
        return modelMapper.map(actor, ActorDTO.class);
    }

    // ActorDTO -> Actor
    public Actor actorDTOToActor(ActorDTO actorDTO) {
        if (actorDTO == null) {
            throw new IllegalArgumentException("ActorDTO cannot be null");
        }
        return modelMapper.map(actorDTO, Actor.class);
    }

    public ActorMoviesDTO actorToActorMoviesDTO(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("Actor cannot be null");
        }

        ActorMoviesDTO dto = modelMapper.map(actor, ActorMoviesDTO.class);

        if (actor.getMovies() != null) {
            dto.setMovies(actor.getMovies().stream()
                    .map(movieMapper::movieToMovieResponseDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public void updateActorFromDTO(ActorDTO actorDTO, Actor actor) {
        if (actorDTO == null || actor == null) {
            throw new IllegalArgumentException("Neither DTO nor Entity can be null");
        }
        modelMapper.map(actorDTO, actor);
    }
}

