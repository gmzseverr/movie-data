package com.i_move.i_movie_spring.repository;

import com.i_move.i_movie_spring.entity.Actor;
import com.i_move.i_movie_spring.entity.Genre;
import com.i_move.i_movie_spring.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Set<Movie> findByGenres(Genre genre);

    Optional<Movie> findByTitleAndDirector_NameAndActorsInAndGenresIn(
            String title, String directorName, List<Actor> actors, List<Genre> genres);

    List<Movie> findByTitle(String title);

    List<Movie> findByActorsInAndGenresIn(List<Actor> actors, List<Genre> genres);
}