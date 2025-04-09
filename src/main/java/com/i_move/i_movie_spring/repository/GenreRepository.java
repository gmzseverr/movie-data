package com.i_move.i_movie_spring.repository;


import com.i_move.i_movie_spring.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional <Genre> findByName(String name);


    @Query("SELECT g FROM Genre g WHERE g.id = :id")
    Optional<Genre> findByIdWithMovies(@Param("id") Long id);



}
