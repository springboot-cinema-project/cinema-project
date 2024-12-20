package com.movie.domain;

import lombok.Data;

@Data
public class Seats {

    private long id;
    private long theaterScreenId;
    private long seatNumber;
    private long seatPrice;

}
