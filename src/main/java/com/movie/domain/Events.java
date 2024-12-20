package com.movie.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Events {

    private long id;
    private String eventTitle;
    private String eventContent;
    private String eventImg;
    private LocalDateTime EventStartDate;
    private LocalDateTime EventEndDate;
    private long couponId;

}
