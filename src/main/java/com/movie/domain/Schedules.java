package com.movie.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Schedules {

    private long id;
    private int screenId;
    private long movieId;
    private LocalDate watchDate;
    private LocalTime startTime;
    private LocalTime endTime;

}
