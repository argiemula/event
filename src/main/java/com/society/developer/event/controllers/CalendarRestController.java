package com.society.developer.event.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.society.developer.event.Exception.BadDateFormatException;
import com.society.developer.event.models.Event;
import com.society.developer.event.services.EventService;

@RestController
public class CalendarRestController {

	@Autowired
	private EventService eventService;
	
	@RequestMapping(value = "/eventList", method = RequestMethod.GET)
	public List<Event> findAllEvent(){
		return eventService.findAll();
	}
	
	
	@RequestMapping(value="/saveEvent", method=RequestMethod.POST)
	public Event addEvent(@RequestBody Event event) {
		Event created = eventService.save(event);
		return created; 
	}
	
	@RequestMapping(value="/updateEvent", method=RequestMethod.PATCH)
	public Event updateEvent(@RequestBody Event event) {
		return eventService.save(event);
	}
	
	@RequestMapping(value="/deleteEvent", method=RequestMethod.DELETE)
	public void removeEvent(@RequestBody Event event) {
		eventService.delete(event);
	}
	
	public List<Event> getEventsInRange(@RequestParam(value = "start", required = true) String start, 
										@RequestParam(value = "end", required = true) String end){
		Date startDate = null;
		Date endDate = null;
		SimpleDateFormat inputDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			startDate = inputDateFormat.parse(start);
		} catch (ParseException e) {
			throw new BadDateFormatException("bad start date: " + start);
		}
		
		try {
			endDate = inputDateFormat.parse(end);
		} catch (ParseException e) {
			throw new BadDateFormatException("bad end date: " + end);
		}
		
		LocalDateTime startDateTime = LocalDateTime.ofInstant(startDate.toInstant(),
                ZoneId.systemDefault());
		
		LocalDateTime endDateTime = LocalDateTime.ofInstant(endDate.toInstant(),
                ZoneId.systemDefault());
		
		return eventService.findByDateBetween(startDateTime, endDateTime);
	}
}
