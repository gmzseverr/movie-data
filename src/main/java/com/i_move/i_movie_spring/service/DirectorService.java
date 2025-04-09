package com.i_move.i_movie_spring.service;


import com.i_move.i_movie_spring.dto.DirectorDTO;

import java.util.List;


public interface DirectorService {
  DirectorDTO findById(Long id);
  DirectorDTO save(DirectorDTO directorDTO);
  DirectorDTO delete(Long id);
  DirectorDTO findByName(String name);
  List<DirectorDTO> findAll();

}
