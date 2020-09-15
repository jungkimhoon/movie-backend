package com.example.movie.controller;

import com.example.movie.dto.MovieDTO;
import com.example.movie.dto.UserDTO;
import com.example.movie.dto.UserRepository;
import org.springframework.web.bind.annotation.*;
import com.example.movie.service.MovieService;

import java.util.HashMap;
import java.util.List;

@RestController
public class MovieController {
    private MovieService movieService;
    private JwtService jwtService;

    private UserRepository userRepository;

    public MovieController(MovieService movieService, UserRepository userRepository, JwtService jwtService) {
        this.movieService = movieService;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
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

    @PostMapping("/users")
    public void signUp(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getAge());
        UserDTO savedUser = userRepository.save(userDTO);
    }

    @PostMapping("/user")
    public UserDTO login(@RequestBody HashMap<String, String> map){
        UserDTO loginUser = userRepository.findMember(map.get("email"), map.get("password"));
//        String token = jwtService.create()
        if(loginUser == null) return null;
        else return loginUser;
    }
}
