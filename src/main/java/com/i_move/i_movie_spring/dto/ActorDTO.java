package com.i_move.i_movie_spring.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorDTO {

    private Long id;
    @NotBlank(message = "Actor name cannot be blank")
    @Size(max = 100, message = "Actor name cannot exceed 100 characters")
    private String name;

}
