package com.i_move.i_movie_spring.service;

import com.i_move.i_movie_spring.dto.DirectorDTO;
import com.i_move.i_movie_spring.entity.Director;
import com.i_move.i_movie_spring.exceptions.DirectorNotFoundException;
import com.i_move.i_movie_spring.exceptions.GlobalExceptionHandler;
import com.i_move.i_movie_spring.exceptions.MovieException;
import com.i_move.i_movie_spring.mapper.DirectorMapper;
import com.i_move.i_movie_spring.repository.DirectorRepository;
import com.i_move.i_movie_spring.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    @Override
    public DirectorDTO findById(Long id) {
        Optional<Director> director = directorRepository.findById(id);
        return director.map(directorMapper::directorToDirectorDTO).orElse(null);
    }

    @Override
    public DirectorDTO save(DirectorDTO directorDTO) {
        Director director = directorMapper.toEntity(directorDTO);
        Director savedDirector = directorRepository.save(director);
        return directorMapper.directorToDirectorDTO(savedDirector);
    }

    @Override
    public DirectorDTO delete(Long id) {
        Optional<Director> director = directorRepository.findById(id);
        if (director.isPresent()) {
            directorRepository.delete(director.get());
            return directorMapper.directorToDirectorDTO(director.get());
        }
        return null;
    }

    @Override
    public DirectorDTO findByName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Director name cannot be null or empty");
        }

        return directorRepository.findByName(name)
                .map(directorMapper::directorToDirectorDTO)
                .orElseThrow(() -> new DirectorNotFoundException("Director not found with name: " + name));
    }
    @Override
    public List<DirectorDTO> findAll() {
        List<Director> directors = directorRepository.findAll();
        return directors.stream()
                .map(directorMapper::directorToDirectorDTO)
                .toList();
    }
}
