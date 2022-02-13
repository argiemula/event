package com.society.developer.event.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.developer.event.models.Event;
import com.society.developer.event.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepo;
	
	public List<Event> findAll(){
		return eventRepo.findAll();
	}
	
	public Event save(Event event) {
		return eventRepo.save(event);
	}
	
	public Event findById(Long id) {
		return eventRepo.findById(id).orElse(null);
	}
	
	public void delete(Event event) {
		eventRepo.delete(event);
	}
	
	public List<Event> findByDateBetween(LocalDateTime start, LocalDateTime end){
		return eventRepo.findByDateBetween(start, end);
	}
}
