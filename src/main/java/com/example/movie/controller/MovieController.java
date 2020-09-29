package com.example.movie.controller;

import com.example.movie.dto.movie.MovieDTO;
import com.example.movie.dto.user.UserRepository;
import com.example.movie.service.JwtServiceImpl;
import org.springframework.web.bind.annotation.*;
import com.example.movie.service.MovieService;

import java.util.*;

@RestController
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie/{search}/{page}")
    public List<MovieDTO> serchMovie(@PathVariable String search, @PathVariable int page){
        List<MovieDTO> movieDTOList = movieService.movieSearch(search, page);
        return movieDTOList;
    }
}
