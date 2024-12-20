package com.movie.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Schedules {

    private long id;
    private long theaterScreenId;
    private long movieId;
    private LocalDateTime watchDate;

}
