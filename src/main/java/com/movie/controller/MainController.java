package com.movie.controller;

import com.movie.domain.Events;
import com.movie.domain.Movies;
import com.movie.service.EventService;
import com.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

	@Autowired
	public final EventService eventService;
	public final MovieService movieService;

	@GetMapping("/")
	public String index(Model model) {

		List<Movies> upcoming = movieService.upcomingMovie();
		List<Movies> released = movieService.releasedMovie();
		List<Movies> best = movieService.bestMovies();
		model.addAttribute("upcoming", upcoming);
		model.addAttribute("released", released);
		model.addAttribute("best", best);
		System.out.println(released);

		List<Events> mainEvents = eventService.eventStart();
		model.addAttribute("event",mainEvents);

		model.addAttribute("content", "main/index");
		model.addAttribute("title", "ホーム");
		return "layout/base";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}


}
