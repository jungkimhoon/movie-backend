package com.example.movie.controller;

import com.example.movie.dto.MovieDTO;
import com.example.movie.dto.UserDTO;
import com.example.movie.dto.UserRepository;
import com.example.movie.exception.UnauthorizedException;
import com.example.movie.service.JwtServiceImpl;
import org.springframework.web.bind.annotation.*;
import com.example.movie.service.MovieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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

    @PostMapping("/users")
    public void signUp(@RequestBody UserDTO userDTO){
        userRepository.save(userDTO);
    }

    @PostMapping("/user")
    public void login(@RequestBody HashMap<String, String> map, HttpServletResponse response){
        System.out.println(map.get("email"));

        UserDTO loginUser = userRepository.findMember(map.get("email"), map.get("password"));

        if(loginUser == null) new UnauthorizedException();
        else{
            String token = jwtService.createToken(loginUser.getId()+"", (60 * 1000 * 60));
            HashMap<String, Object> tokenMap = new HashMap<>();
            System.out.println(token);
            response.setHeader("Authorization", token);
        }
    }

    @PutMapping("/myPage")
    public void updateMyInfo(@RequestBody HashMap<String, String> map, HttpServletRequest request){
        String token = request.getHeader("authorization");
        String id = jwtService.getSubject(token);
        System.out.println(id);
        System.out.println(map.get("country"));
        userRepository.updateMember(map.get("age"), map.get("email"), map.get("country"), id);
    }

    @PostMapping("/myPage")
    public UserDTO myPage(HttpServletRequest request){
        String token = request.getHeader("authorization");
        System.out.println(token);
        int id = Integer.parseInt(jwtService.getSubject(token));
        Optional<UserDTO> userDTO = userRepository.findById(id);
        System.out.println(userDTO);
        return userDTO.get();
    }
}
