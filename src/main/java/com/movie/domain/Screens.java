package com.movie.domain;

import lombok.Data;

import java.util.List;

@Data
public class Screens {

    private long id;
    private String screenName;
    private long totalSeat;

    private List<Seats> seatList;
}
