package com.example.movie.dto.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface MovieBoardRepository extends JpaRepository<MovieBoardDTO, Integer> {
    public List<MovieBoardDTO> findAllByOrderByIdDesc();
}
