package com.i_move.i_movie_spring.config;


import com.i_move.i_movie_spring.dto.ActorMoviesDTO;
import com.i_move.i_movie_spring.dto.GenreMoviesDTO;
import com.i_move.i_movie_spring.entity.Actor;
import com.i_move.i_movie_spring.entity.Genre;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Genre.class, GenreMoviesDTO.class)
                .addMappings(mapper -> {
                    mapper.skip(GenreMoviesDTO::setMovies);
                });
        modelMapper.createTypeMap(Actor.class, ActorMoviesDTO.class)
                .addMappings(mapper -> {
                    mapper.skip(ActorMoviesDTO::setMovies);
                });

        return modelMapper;
    }
}