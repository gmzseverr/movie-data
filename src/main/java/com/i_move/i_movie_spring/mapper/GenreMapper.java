package com.i_move.i_movie_spring.mapper;

import com.i_move.i_movie_spring.dto.ActorDTO;
import com.i_move.i_movie_spring.dto.GenreDTO;
import com.i_move.i_movie_spring.dto.GenreMoviesDTO;
import com.i_move.i_movie_spring.entity.Actor;
import com.i_move.i_movie_spring.entity.Genre;
import com.i_move.i_movie_spring.entity.Movie;
import com.i_move.i_movie_spring.repository.GenreRepository;
import com.i_move.i_movie_spring.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GenreMapper {

    private final MovieMapper movieMapper;
    private final ModelMapper modelMapper;

    public Genre genreDTOToGenre(GenreDTO genreDTO) {
        if (genreDTO == null) {
            throw new IllegalArgumentException("GenreDTO cannot be null");
        }
        return modelMapper.map(genreDTO, Genre.class);
    }

    public GenreDTO genreToGenreDTO(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Genre cannot be null");
        }
        return modelMapper.map(genre, GenreDTO.class);
    }


    //Filmlerler
    public GenreMoviesDTO genreToGenreWithMoviesDTO(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Genre cannot be null");
        }

        GenreMoviesDTO dto = modelMapper.map(genre, GenreMoviesDTO.class);

        // Filmleri özel mapper ile dönüştür
        if (genre.getMovies() != null) {
            dto.setMovies(genre.getMovies().stream()
                    .map(movieMapper::movieToMovieResponseDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

}
