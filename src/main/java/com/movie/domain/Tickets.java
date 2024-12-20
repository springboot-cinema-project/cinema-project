package com.movie.domain;

import lombok.Data;

@Data
public class Tickets {

    private long id;
    private long price;
    private long ticket_status;
    private long seatId;
    private long booking_id;

}
