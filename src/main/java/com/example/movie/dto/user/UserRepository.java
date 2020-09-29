package com.example.movie.dto.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<UserDTO, Integer> {

    @Query(value = "select * from movie_user where email = :email and password = :password", nativeQuery = true)
    public UserDTO findMember(String email, String password);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update movie_user set age = :age, email = :email, country = :country where id = :id", nativeQuery = true)
    public void updateMember(String age, String email, String country, String id);
}
