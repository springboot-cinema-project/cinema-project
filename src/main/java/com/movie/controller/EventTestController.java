package com.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class EventTestController {
	
	@GetMapping("/sm")
	public String sm(Model model) {
		
		model.addAttribute("title", "smkun");
		model.addAttribute("content", "events/event_single");
		
		return "layout/base";
	}
}
