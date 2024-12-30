package com.movie.controller;

import com.movie.domain.MovieDetails;
import com.movie.domain.Movies;
import com.movie.domain.ScreenScheduleSeatDto;
import com.movie.domain.Seats;
import com.movie.service.MovieDetailService;
import com.movie.service.MovieService;
import com.movie.service.ScheduleService;
import com.movie.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final ScheduleService scheduleService;
    private final MovieService movieService;
    private final SeatService seatService;
    private final MovieDetailService movieDetailService;

    @GetMapping("/schedule")
    public String movieScheduleList(long movieId, Model model) {

        List<ScreenScheduleSeatDto> schedules = scheduleService.movieSchedulesList(movieId);
        Movies movies = movieService.movieInfo(movieId);
        MovieDetails movieDetails = movieDetailService.getMovieDetail(movieId);

        Set<LocalDate> dates = schedules.stream()
                .map(ScreenScheduleSeatDto::getWatchDate)
                .collect(Collectors.toCollection(TreeSet::new));

        List<String> desiredFormats = List.of("2D", "3D", "4DX", "IMAX");

        // 스케줄에서 사용 가능한 형식 추출 (중복 없이)
        Set<String> availableFormats = schedules.stream()
                .map(ScreenScheduleSeatDto::getScreenName)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        // 원하는 순서대로 필터링된 형식 리스트 생성
        List<String> formats = desiredFormats.stream()
                .filter(availableFormats::contains)
                .collect(Collectors.toList());

        model.addAttribute("content", "movies/movie_booking");
        model.addAttribute("title", "スケジュール");
        model.addAttribute("schedules", schedules);
        model.addAttribute("movie", movies);
        model.addAttribute("detail", movieDetails);
        model.addAttribute("dates", dates);
        model.addAttribute("formats", formats);

        return "layout/base";

    }

    @GetMapping("/seat")
    public String getSeat(long scheduleId, Model model) {
        ScreenScheduleSeatDto seats = seatService.getSeat(scheduleId);
        Movies movies = movieService.movieInfo(seats.getMovieId());

        // 좌석 데이터를 열별로 그룹화 (seatColumn을 String으로 변환)
        Map<String, List<Seats>> groupedSeats = seats.getSeatList().stream()
                .collect(Collectors.groupingBy(seat -> String.valueOf(seat.getSeatColumn())));

        model.addAttribute("groupedSeats", groupedSeats); // 그룹화된 데이터를 추가
        model.addAttribute("movie", movies);
        model.addAttribute("schedule", seats);

        return "movies/seat_booking";
    }

    @PostMapping("/booking/checkSeatState")
    @ResponseBody
    public long checkSeatState(@RequestParam List<Long> seatIds) {
        return seatService.checkSeatState(seatIds);
    }

    @PostMapping("/proceed")
    public String bookingProceed(@RequestParam("selectedSeatId") String selectedSeatId, Model model, @RequestParam("scheduleId") long scheduleId) {

        // 문자열을 Long 배열로 변환
        String[] seatIdStrings = selectedSeatId.split(",");
        Long[] seatIds = Arrays.stream(seatIdStrings)
                .map(Long::valueOf) // 문자열을 Long으로 변환
                .toArray(Long[]::new); // Long[] 배열로 변환

        seatService.bookingStats(seatIds);

        List<Seats> seats = seatService.bookingSeats(seatIds);
        ScreenScheduleSeatDto schedule = seatService.getScreenScheduleByScheduleId(scheduleId);
        Movies movie = movieService.movieInfo(schedule.getMovieId());

        model.addAttribute("seats", seats);
        model.addAttribute("schedule", schedule);
        model.addAttribute("movie", movie);

        return "booking/booking_type";
    }
}
