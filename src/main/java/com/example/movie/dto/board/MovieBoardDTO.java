package com.example.movie.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="movieboard")
public class MovieBoardDTO {

    @Id
    @GeneratedValue
    private int id;
    private String subject;
    private String writerName;
    private String content;
    private Date writeDate;
}
