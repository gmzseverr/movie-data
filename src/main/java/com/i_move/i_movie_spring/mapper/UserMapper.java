package com.i_move.i_movie_spring.mapper;

import com.i_move.i_movie_spring.dto.RegisterUser;
import com.i_move.i_movie_spring.dto.UserResponseDTO;
import com.i_move.i_movie_spring.entity.user.ApplicationUser;
import com.i_move.i_movie_spring.entity.user.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor

public class UserMapper {

    private final ModelMapper modelMapper;

    // Entity -> DTO
    public UserResponseDTO UserToUserResponseDTO(ApplicationUser user) {
        if (user == null) return null;

        UserResponseDTO userResponseDto = modelMapper.map(user, UserResponseDTO.class);
        userResponseDto.setRoles(mapRoles(user.getAuthorities()));
        return userResponseDto;
    }

    // DTO -> Entity
    public ApplicationUser toApplicationUser(RegisterUser registerDto) {
        if (registerDto == null) return null;

        ApplicationUser user = modelMapper.map(registerDto, ApplicationUser.class);
        user.setPassword(null);
        return user;
    }

    private Set<String> mapRoles(Collection<? extends GrantedAuthority> authorities) {
        if (authorities == null) return Collections.emptySet();

        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }
}