package com.society.developer.event.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.society.developer.event.models.Event;
import com.society.developer.event.services.EventService;

@RestController
public class CalendarController {

	@Autowired
	private EventService eventService;
	
	@RequestMapping(value = "/eventList", method = RequestMethod.GET)
	public List<Event> findAllEvent(){
		return eventService.findAll();
	}
	
}
