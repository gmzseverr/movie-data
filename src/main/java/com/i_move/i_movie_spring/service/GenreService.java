package com.i_move.i_movie_spring.service;


import com.i_move.i_movie_spring.dto.GenreDTO;
import com.i_move.i_movie_spring.dto.GenreMoviesDTO;
import com.i_move.i_movie_spring.dto.MovieResponse;
import com.i_move.i_movie_spring.entity.Genre;

import java.util.List;
import java.util.Optional;


public interface GenreService {
  GenreDTO createGenre(GenreDTO genreDTO);


  // Sorgu İşlemleri
  Optional<GenreDTO> findById(Long id);
  Optional<GenreDTO> findByName(String name);
  List<GenreDTO> findAllGenres();

  // İlişkisel İşlemler
  GenreMoviesDTO getGenreWithMovies(Long id);
  List<MovieResponse> getMoviesByGenreId(Long id);
}
