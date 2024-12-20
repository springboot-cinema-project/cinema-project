package com.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class EventController {

	@GetMapping("/single")
	public String eventSingle(Model model) {
		model.addAttribute("content", "events/event_single");
		model.addAttribute("title", "smkun");

		return "layout/base";
	}

	@GetMapping("/category")
	public String eventCategory(Model model) {
		model.addAttribute("content","events/event_category");
		model.addAttribute("title","smkun2");

		return  "layout/base";
	}
}
