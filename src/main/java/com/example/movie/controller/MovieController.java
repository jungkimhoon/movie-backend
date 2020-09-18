package com.example.movie.controller;

import com.example.movie.dto.MovieDTO;
import com.example.movie.dto.UserDTO;
import com.example.movie.dto.UserRepository;
import com.example.movie.service.JwtServiceImpl;
import org.springframework.web.bind.annotation.*;
import com.example.movie.service.MovieService;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {
    private MovieService movieService;
    private JwtServiceImpl jwtService;

    private UserRepository userRepository;

    public MovieController(MovieService movieService, UserRepository userRepository, JwtServiceImpl jwtService) {
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
    public Map<String, Object> login(@RequestBody HashMap<String, String> map){
        UserDTO loginUser = userRepository.findMember(map.get("email"), map.get("password"));
        if(loginUser == null) return null;
        else{
            String token = jwtService.createToken(map.get("email"), (2*1000*60));
            HashMap<String, Object> tokenMap = new HashMap<>();
            System.out.println(token);
            tokenMap.put("token", token);
            return tokenMap;
        }
    }
}
