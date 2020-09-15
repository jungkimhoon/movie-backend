package com.example.movie.controller;

import com.example.movie.dto.MovieDTO;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.movie.service.MovieService;

import java.util.List;

@RestController
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie/{search}")
    public List<MovieDTO> serchMovie(@PathVariable String search){
        List<MovieDTO> movieDTOList = movieService.movieSearch(search);
        return movieDTOList;
    }

    @GetMapping("/hello")
    public String helloworld() {
        return "Hello world";
    }
}
