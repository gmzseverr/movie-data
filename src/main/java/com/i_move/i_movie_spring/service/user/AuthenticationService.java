package com.i_move.i_movie_spring.service.user;

import com.i_move.i_movie_spring.dto.UserResponseDTO;
import com.i_move.i_movie_spring.entity.user.ApplicationUser;
import com.i_move.i_movie_spring.entity.user.Role;
import com.i_move.i_movie_spring.exceptions.EmailAlreadyExistsException;
import com.i_move.i_movie_spring.exceptions.NotFoundException;
import com.i_move.i_movie_spring.mapper.UserMapper;
import com.i_move.i_movie_spring.repository.RoleRepository;
import com.i_move.i_movie_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager; // Enjekte edilen AuthenticationManager

    @Transactional
    public UserResponseDTO register(String fullName, String email, String password) {
        userRepository.findUserByEmail(email).ifPresent(user -> {
            throw new EmailAlreadyExistsException(email);
        });

        Role userRole = roleRepository.findByAuthority("USER")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setAuthority("USER");
                    return roleRepository.save(newRole);
                });

        ApplicationUser user = new ApplicationUser();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setAuthorities(Set.of(userRole));

        ApplicationUser savedUser = userRepository.save(user);
        return userMapper.UserToUserResponseDTO(savedUser);
    }

    @Transactional
    public UserResponseDTO login(String email, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            ApplicationUser user = userRepository.findUserByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Invalid email or password"));

            Set<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());

            return new UserResponseDTO(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    roles
            );

        } catch (Exception e) {
            throw new RuntimeException("Invalid email or password");
        }
    }


}
