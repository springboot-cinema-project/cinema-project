package com.movie.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Movies {

    private long id;
    private String title;
    private String engTitle;
    private long runningTime;
    private LocalDate releaseDate;
    private LocalDate endDate;
    private long audience;
    private String country;
    private String production;
    private String distribution;
    private String genre;
    private String director;
    private String actor;
    private double book_rate;
    private String posterImg;
    private String content;

}
