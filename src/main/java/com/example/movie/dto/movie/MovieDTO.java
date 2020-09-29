package com.example.movie.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MovieDTO {
    private String title;
    private String link;
    private String image;
    private String pubDate;
    private String director;
    private String actor;
    private double userRating;
}
