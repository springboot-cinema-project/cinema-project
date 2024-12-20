package com.movie.domain;

import lombok.Data;

@Data
public class Coupons {

    private long id;
    private String couponTitle;
    private String couponType;
    private String couponPrice;
}
