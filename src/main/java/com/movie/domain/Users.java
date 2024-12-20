package com.movie.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Users {

    private long id;
    private String email;
    private String name;
    private String phone;
    private String password;
    private String role;
    private String gps;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
