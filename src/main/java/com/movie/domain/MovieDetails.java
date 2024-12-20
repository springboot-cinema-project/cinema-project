package com.movie.domain;

import lombok.Data;

@Data
public class MovieDetails {

    private long id;
    private long movieId;
    private String trailer;
    private String movieImg;
}
