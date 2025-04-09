package com.i_move.i_movie_spring.mapper;

import com.i_move.i_movie_spring.dto.DirectorDTO;
import com.i_move.i_movie_spring.entity.Director;
import com.i_move.i_movie_spring.entity.Movie;
import com.i_move.i_movie_spring.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class DirectorMapper {

    private final MovieRepository movieRepository;

    public Director toEntity(DirectorDTO directorDTO) {

        Director director = new Director();
        director.setName(directorDTO.getName());
        return director;
    }

    public DirectorDTO directorToDirectorDTO(Director director) {
        DirectorDTO directorDTO = new DirectorDTO();
        directorDTO.setId(director.getId());
        directorDTO.setName(director.getName());
        return directorDTO;
}}
