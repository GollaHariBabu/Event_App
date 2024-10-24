package org.jsp.event_app.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsp.event_app.dao.EventDao;
import org.jsp.event_app.entity.Event;
import org.jsp.event_app.exceptionclasses.InvalidEventIdException;
import org.jsp.event_app.exceptionclasses.NoEventFoundException;
import org.jsp.event_app.responseStructure.ResponseStructure;
import org.jsp.event_app.service.EventService;
import org.jsp.event_app.util.EventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDao eventDao;

	@Override
	public ResponseEntity<?> saveEvent(Event event) {
		event.setStatus(EventStatus.UP_COMING);
		Event dbEvent = eventDao.saveEvent(event);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Event Saved Successfully...").body(dbEvent).build());
	}

	@Override
	public ResponseEntity<?> updateEvent(Event event) {
		event.setStatus(EventStatus.UP_COMING);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Event updated successfully...").body(eventDao.updateEvent(event)).build());
	}

	@Override
	public ResponseEntity<?> findAllEvents() {
		List<Event> events = eventDao.findAllEvents();
		if (events.isEmpty()) {
			throw NoEventFoundException.builder().message("No Event found in the database table...").build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Events found successfully...").body(events).build());
	}

	@Override
	public ResponseEntity<?> findEventById(int id) {
		Optional<Event> optional = eventDao.findEventById(id);
		if (optional.isEmpty()) {
			throw InvalidEventIdException.builder().message("No Event found with the id in the database table...")
					.build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Event found successfully...").body(optional).build());
	}

	@Override
	public ResponseEntity<?> findAllUpcomingEvents() {
		List<Event> events = eventDao.findAllUpcomingEvents();
		if (events.isEmpty())
			throw NoEventFoundException.builder().message("No UP_COMING events found in the database table...").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All UP_COMING Events found successfully...").body(events).build());
	}

	@Override
	public ResponseEntity<?> findAllOngoingEvents() {
		List<Event> events = eventDao.findAllOngoingEvents();
		if (events.isEmpty())
			throw NoEventFoundException.builder().message("No ON_GOING events found in the database table...").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All ON_GOING Events found successfully...").body(events).build());
	}

	@Override
	public ResponseEntity<?> findAllCompletedEvents() {
		List<Event> events = eventDao.findAllCompletedEvents();
		if (events.isEmpty())
			throw NoEventFoundException.builder().message("No COMPLETED events found in the database table...").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All COMPLETED Events found successfully...").body(events).build());
	}

	@Override
	public ResponseEntity<?> findAllDeletedEvents() {
		List<Event> events = eventDao.findAllDeletedEvents();
		if (events.isEmpty())
			throw NoEventFoundException.builder().message("No DELETED events found in the database table...").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All DELETED Events found successfully...").body(events).build());
	}

	@Override
	public ResponseEntity<?> setEventStatusToOngoing(int id) {
		Optional<Event> event = eventDao.findEventById(id);
		if (event.isEmpty())
			throw InvalidEventIdException.builder().message("Invalid Event Id... Unable to set status to ON_GOING ")
					.build();
		Event e = event.get();
		e.setStatus(EventStatus.ON_GOING);
		e = eventDao.updateEvent(e);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Successfully updated Event status to ON_GOING...").body(e).build());
	}

	@Override
	public ResponseEntity<?> setEventStatusToCompleted(int id) {
		Optional<Event> event = eventDao.findEventById(id);
		if (event.isEmpty())
			throw InvalidEventIdException.builder().message("Invalid Event Id... Unable to set status to COMPLETED ")
					.build();
		Event e = event.get();
		e.setStatus(EventStatus.COMPLETED);
		e = eventDao.updateEvent(e);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Successfully updated Event status to COMPLETED...").body(e).build());
	}

	@Override
	public ResponseEntity<?> setEventStatusToDeleted(int id) {
		Optional<Event> event = eventDao.findEventById(id);
		if (event.isEmpty())
			throw InvalidEventIdException.builder().message("Invalid Event Id... Unable to set status to DELETED ")
					.build();
		Event e = event.get();
		e.setStatus(EventStatus.DELETED);
		e = eventDao.updateEvent(e);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Successfully updated Event status to DELETED...").body(e).build());
	}

	@Override
	public ResponseEntity<?> findEventsBetweenDates(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
		List<Event> events = eventDao.findAllEvents();
		if (events.isEmpty())
			throw NoEventFoundException.builder().message("No Event found in the database table...").build();
		ArrayList<Event> eventInRange = new ArrayList<>();
		for (Event e : events) {
			if (e.getFromDateTime().isAfter(fromDateTime) && e.getToDateTime().isBefore(toDateTime)) {
				eventInRange.add(e);
			}
		}
		if (eventInRange.isEmpty())
			throw NoEventFoundException.builder().message("No Event found in the database table...").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Events Found in the Given Date Range...").body(eventInRange).build());
	}
}
