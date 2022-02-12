package com.society.developer.event;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {

	@GetMapping("/index")
	public String getIndex() {
		return "index";
	}
	
	@GetMapping("/view/calendar")
	public String viewCalendar() {
		return "calendar";
	}
	
}
