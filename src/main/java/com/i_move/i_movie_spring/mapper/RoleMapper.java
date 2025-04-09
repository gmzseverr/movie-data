package com.i_move.i_movie_spring.mapper;

import com.i_move.i_movie_spring.entity.user.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public String roleToString(Role role) {
        return role != null ? role.getAuthority() : null;
    }

    public Role stringToRole(String authority) {
        if (authority == null) return null;
        Role role = new Role();
        role.setAuthority(authority);
        return role;
    }
}