package com.society.developer.event.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.society.developer.event.models.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

	/* This one uses a JPQL */
	public List<Event> findByStartGreaterThanEqualAndFinishLessThanEqual(LocalDateTime start, LocalDateTime end);
	
	/* This one uses an @Query */
	@Query("select b from Event b where b.start >= ?1 and b.finish <= ?2")
	public List<Event> findByDateBetween(LocalDateTime start, LocalDateTime end);
}
