package com.movie.mapper;

import com.movie.domain.Seats;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SeatMapper {

    public long insertSeat(List<Seats> seats);

}
