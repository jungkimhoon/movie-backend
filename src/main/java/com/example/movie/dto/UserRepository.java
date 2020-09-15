package com.example.movie.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserDTO, Integer> {

    @Query(value = "select * from movie_user where email = :email and password = :password", nativeQuery = true)
    public UserDTO findMember(String email, String password);
}
