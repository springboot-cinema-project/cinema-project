package com.movie.domain;

import lombok.Data;

@Data
public class Seats {

    private long id;
    private int screenId;
    private long scheduleId;
    private char seatColumn;
    private long seatRow;
    private long seatPrice;

}
