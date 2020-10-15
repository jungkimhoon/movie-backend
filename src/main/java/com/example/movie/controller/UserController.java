package com.example.movie.controller;

import com.example.movie.dto.user.UserDTO;
import com.example.movie.dto.user.UserRepository;
import com.example.movie.exception.UnauthorizedException;
import com.example.movie.service.JwtServiceImpl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Optional;

@RestController
public class UserController {
    private JwtServiceImpl jwtService;
    private UserRepository userRepository;

    public UserController(UserRepository userRepository, JwtServiceImpl jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/users")
    public void signUp(@RequestBody UserDTO userDTO){
        userRepository.save(userDTO);
    }

    @PostMapping("/user")
    public void login(@RequestBody HashMap<String, String> map, HttpServletResponse response){
        System.out.println(map.get("email"));

        UserDTO loginUser = userRepository.findMember(map.get("email"), map.get("password"));
        if(loginUser == null) {
            new UnauthorizedException();
            return;
        }
        else{
            String token = jwtService.createToken(loginUser.getId()+"", (60 * 1000 * 60));
            System.out.println(token);
            response.setHeader("authorization", token);
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
