package com.movie.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Bookings {

    private long id;
    private long usersId;
    private long scheduleId;
    private LocalDateTime timestamp;
}
