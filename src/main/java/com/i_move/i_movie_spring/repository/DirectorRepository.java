package com.i_move.i_movie_spring.repository;

import com.i_move.i_movie_spring.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
Optional <Director> findByName(String name);

}
