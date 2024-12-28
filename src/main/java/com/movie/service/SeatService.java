package com.movie.service;

import com.movie.domain.ScreenScheduleSeatDto;
import com.movie.domain.Seats;
import com.movie.mapper.SeatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatMapper seatMapper;

    public ScreenScheduleSeatDto getSeat(long scheduleId) {
        return seatMapper.getSeat(scheduleId);
    }

    public long checkSeatState(List<Long> seatIds) {

        long result = 0;

        for (Long id : seatIds) {

            Seats seat = seatMapper.checkSeat(id);

            if (seat.getState() != 0) {
                result = 1;
            }
        }

        return result;

    }

    public void bookingStats(Long[] seatIds) {

        for (Long id : seatIds) {
            seatMapper.bookingStats(id);
        }

    }

    public List<Seats> bookingSeats(Long[] seatIds) {

        List<Seats> seats = new ArrayList<>();

        for (long id : seatIds) {
            Seats seat = seatMapper.checkSeat(id);
            seats.add(seat);
        }
        return seats;
    }

    public ScreenScheduleSeatDto getScreenScheduleByScheduleId(long scheduleId) {
        return seatMapper.getScreenScheduleByScheduleId(scheduleId);
    }
}
