package com.i_move.i_movie_spring.repository;

import com.i_move.i_movie_spring.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    Optional<Actor> findByName(String name);

    @Query("SELECT a FROM Actor a WHERE a.id = :id")
    Optional<Actor> findByIdWithMovies(@Param("id") Long id);

}
