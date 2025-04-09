package com.i_move.i_movie_spring.service;

import com.i_move.i_movie_spring.dto.ActorDTO;
import com.i_move.i_movie_spring.dto.ActorMoviesDTO;
import com.i_move.i_movie_spring.dto.MovieResponse;
import com.i_move.i_movie_spring.entity.Actor;
import com.i_move.i_movie_spring.entity.Genre;
import com.i_move.i_movie_spring.exceptions.NotFoundException;
import com.i_move.i_movie_spring.mapper.ActorMapper;
import com.i_move.i_movie_spring.mapper.MovieMapper;
import com.i_move.i_movie_spring.repository.ActorRepository;
import com.i_move.i_movie_spring.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final MovieMapper movieMapper;
    private final ActorMapper actorMapper;


    @Override
    public ActorDTO createActor(ActorDTO actorDTO) {
        return null;
    }

    @Override
    public ActorDTO findById(Long id) {
        Optional<Actor> actorOptional = actorRepository.findById(id);
        return actorOptional.map(actorMapper::actorToActorDTO).orElseThrow(
                () -> new NotFoundException("Actor not found"));


    }


    @Override
    public ActorDTO findByName(String name) {
        Optional<Actor> actorOptional = actorRepository.findByName(name);
        return actorOptional.map(actorMapper::actorToActorDTO).orElseThrow(
                () -> new NotFoundException("Actor not found with this name: "+name)
        );
    }

    @Override
    public List<ActorDTO> findAllActors() {
       List<Actor> actors = actorRepository.findAll();
       return actors.stream().map(actorMapper::actorToActorDTO).collect(Collectors.toList());
    }

    @Override
    public ActorMoviesDTO getActorWithMovies(Long id) {
      Optional<Actor> optionalActorMovies = actorRepository.findByIdWithMovies(id);
      return actorMapper.actorToActorMoviesDTO(optionalActorMovies.orElseThrow(
              ()-> new NotFoundException("Not found")
      ));
    }

    @Override
    public List<MovieResponse> getMoviesByActorId(Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Actor not found"));
        return actor.getMovies().stream()
                .map(movieMapper::movieToMovieResponseDTO)
                .collect(Collectors.toList());
    }



    @Override
    public void deleteActor(Long id) {
        if (!actorRepository.existsById(id)){
            throw new NotFoundException("Actor not found");
        }
        actorRepository.deleteById(id);
    }


}
