package com.i_move.i_movie_spring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "actor",schema = "movie-data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "actor_movie", schema = "movie-data",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @JsonBackReference
    private List<Movie> movies;



    public Actor(String actorName){
        this.name=actorName;
    }

    public void addMovie(Movie movie) {
        if (movies == null) {
            movies = new ArrayList<>();
        }
        movies.add(movie);
    }
}
