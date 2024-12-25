package com.movie.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScreenScheduleSeatDto {

    private long scheduleId;
    private long movieId;
    private LocalDate watchDate;
    private LocalTime startTime;
    private LocalTime endTime;

    private String screenName;
    private String screenType;

    private long seatPrice;
}
