package com.movie.controller;

import com.movie.domain.Movies;
import com.movie.domain.Schedules;
import com.movie.domain.ScreenScheduleSeatDto;
import com.movie.mapper.MovieMapper;
import com.movie.mapper.ScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final ScheduleMapper scheduleMapper;
    private final MovieMapper movieMapper;

    @GetMapping("/schedule")
    public String movieScheduleList(long movieId, Model model) {

        List<ScreenScheduleSeatDto> schedules = scheduleMapper.movieScheduleList(movieId);
        Movies movies = movieMapper.movieDetail(movieId);

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
        model.addAttribute("dates", dates);
        model.addAttribute("formats", formats);

        return "layout/base";

    }
}
