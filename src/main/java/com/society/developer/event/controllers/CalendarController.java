package com.society.developer.event.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.society.developer.event.models.Event;
import com.society.developer.event.services.EventService;



@Controller
public class CalendarController {

	@Autowired
	private EventService eventService;


	
	public Model addModelAttribute(Model model) {
		model.addAttribute("events", eventService.findAll());
		return model;
	}

	// return all record

	@GetMapping("/get/events")
	public String findAllEvents(Model model) {
		addModelAttribute(model);
		return "events";
	}

	// Get Add Form
	@GetMapping("/add/event")
	public String addEvent(@ModelAttribute("event") Event event,Model model) {
		model.addAttribute("event",event);
		return "addEvent";
	}

	// Save to Database
	@PostMapping(value = "/add/event")
	public String saveEvent(@Valid Event event, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addEvent";
			
		} else {
			eventService.save(event);
			return "redirect:/events";
		}

	}

	// Get Edit or View Form
	@GetMapping("/get/event/{op}/{id}")
	public String editOrView(@PathVariable Long id, @PathVariable String op, Model model) {
		addModelAttribute(model);
		model.addAttribute("event", eventService.findById(id));
		return op;
	}

	// Update event By Id
	@RequestMapping(value = "/update/event/{id}", method = { RequestMethod.GET, RequestMethod.PUT })
	public String updateEvent(@Valid @ModelAttribute("event") Event event, BindingResult result) {

		if(result.hasErrors()) {
			return "editEvent";
		}else {
			eventService.save(event);
			return "redirect:/events";
		}
		

	}

	// delete event by id
	@RequestMapping(value = "/others/delete/promo/{id}", method = { RequestMethod.GET, RequestMethod.DELETE })
	public String deleteEvent(@PathVariable Long id) {
		eventService.deleteById(id);
		return "redirect:/events";
	}
	
}
