package com.i_move.i_movie_spring.service;

import com.i_move.i_movie_spring.dto.GenreDTO;
import com.i_move.i_movie_spring.dto.GenreMoviesDTO;
import com.i_move.i_movie_spring.dto.MovieResponse;
import com.i_move.i_movie_spring.entity.Genre;
import com.i_move.i_movie_spring.exceptions.MovieException;
import com.i_move.i_movie_spring.exceptions.NotFoundException;
import com.i_move.i_movie_spring.mapper.GenreMapper;
import com.i_move.i_movie_spring.mapper.MovieMapper;
import com.i_move.i_movie_spring.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;
    private final MovieMapper movieMapper;

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
        Genre genre = genreMapper.genreDTOToGenre(genreDTO);
        Genre savedGenre = genreRepository.save(genre);
        return genreMapper.genreToGenreDTO(savedGenre);
    }



    @Override
    public Optional<GenreDTO> findById(Long id) {
       Optional<Genre> genreOptional = genreRepository.findById(id);
       return genreOptional.stream().map(genreMapper::genreToGenreDTO).findFirst();
    }

    @Override
    public Optional<GenreDTO> findByName(String name) {
       Optional<Genre> genreOptional = genreRepository.findByName(name);
        return genreOptional.stream().map(genreMapper::genreToGenreDTO).findFirst();
    }

    @Override
    public List<GenreDTO> findAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream().map(genreMapper::genreToGenreDTO).collect(Collectors.toList());
    }

    @Override
    public GenreMoviesDTO getGenreWithMovies(Long id) {
        Optional<Genre> genre = genreRepository.findByIdWithMovies(id);
        return genreMapper.genreToGenreWithMoviesDTO(genre.orElseThrow(
                () -> new NotFoundException("No mive with th,s genre id ")
                )
        );
    }

    @Override
    public List<MovieResponse> getMoviesByGenreId(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genre not found"));
        return genre.getMovies().stream()
                .map(movieMapper::movieToMovieResponseDTO)
                .collect(Collectors.toList());
    }

}
