package com.i_move.i_movie_spring.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "authority",unique = true)
    private String authority;


}
