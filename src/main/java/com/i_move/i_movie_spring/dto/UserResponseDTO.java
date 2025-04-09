package com.i_move.i_movie_spring.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO{
    Long id;
    String fullName;
    String email;
    Set<String> roles;

}