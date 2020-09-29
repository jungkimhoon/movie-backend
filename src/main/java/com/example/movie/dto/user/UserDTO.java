package com.example.movie.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "movieUser")
public class UserDTO {

    @Id
    @GeneratedValue
    private int id;
    private String email;
    private int age;
    private String password;
    private String confirmPassword;
    private String country;
    private Boolean terms;
}
