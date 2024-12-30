package com.movie.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Bookings {

    private Long id;
    private Long usersId;
    private Long scheduleId;
    private LocalDateTime timestamp;
}
