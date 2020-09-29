package com.example.movie.controller;

import com.example.movie.dto.board.MovieBoardDTO;
import com.example.movie.dto.board.MovieBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class BoardController {
    private MovieBoardRepository movieRepository;

    public BoardController(MovieBoardRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostMapping("/board")
    public void board(@RequestBody MovieBoardDTO movieBoardDTO){
        movieBoardDTO.setWriteDate(new Date());
        movieRepository.save(movieBoardDTO);
    }

    @GetMapping("/board/list/{loadNum}")
    public Page<MovieBoardDTO> boardList(@PathVariable int loadNum){
        Page<MovieBoardDTO> list = movieRepository.findAll(PageRequest.of(loadNum,5, Sort.Direction.DESC, "id"));
        return list;
    }

    @GetMapping("/board/{idx}")
    public MovieBoardDTO getBoard(@PathVariable int idx){
        Optional<MovieBoardDTO> movieBoardDTO = movieRepository.findById(idx);
        System.out.println(movieBoardDTO);
        return movieBoardDTO.get();
    }
}
