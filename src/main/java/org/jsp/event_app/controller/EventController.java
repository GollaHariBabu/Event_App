package org.jsp.event_app.controller;

import java.time.LocalDateTime;

import org.jsp.event_app.entity.Event;
import org.jsp.event_app.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

	@Autowired
	private EventService eventService;

	@Operation(summary = "To Save All the Events...", description = "This API will accept an Event JSON Object and saves it to the Database Table and Returns the Saved Event Entity Object with the Id which is generated automatically...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Event saved successfully..."),
	@ApiResponse(responseCode = "400", description = "Bad request... Unable to save Event") })
	@PostMapping
	public ResponseEntity<?> saveEvent(@RequestBody Event event) {
		return eventService.saveEvent(event);
	}

	@Operation(summary = "To update Event using Id...", description = "This API will accept an Event JSON Object and update according to User and saves it to the Database Table and Returns the Saved Event Entity Object by using Id ...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Event updated successfully..."),
	@ApiResponse(responseCode = "400", description = "Invalid Id... Unable to update Event") })
	@PutMapping
	public ResponseEntity<?> updateEvent(@RequestBody Event event) {
		return eventService.updateEvent(event);
	}

	@Operation(summary = "To Fetch All Events", description = "This API will Fetch All the Events  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All the Events Found Successfully..."),
	@ApiResponse(responseCode = "404", description = "No Events Present in the Database Table...") })
	@GetMapping
	public ResponseEntity<?> findAllEvents() {
		return eventService.findAllEvents();
	}

	@Operation(summary = "To Fetch Event By Id", description = "This API will Fetch the Event By Id  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Event Found Successfully..."),
	@ApiResponse(responseCode = "404", description = "No Event Present in the Database Table...") })
	@GetMapping("/{id}")
	public ResponseEntity<?> findEventById(@PathVariable int id) {
		return eventService.findEventById(id);

	}

	@Operation(summary = "To Fetch All UPCOMING Events", description = "This API will Fetch All UPCOMING the Events  Available in the Database Table...")
	@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "All the UPCOMING Events Found Successfully..."),
	@ApiResponse(responseCode = "404", description = "No UPCOMING Events Present in the Database Table...") })
	@GetMapping(value = "/upcoming")
	public ResponseEntity<?> findAllUpcomingEvents() {
		return eventService.findAllUpcomingEvents();
	}

	@Operation(summary = "To Fetch All ONGOING Events", description = "This API will Fetch All ONGOING the Events  Available in the Database Table...")
	@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "All the ONGOING Events Found Successfully..."),
	@ApiResponse(responseCode = "404", description = "No ONGOING Events Present in the Database Table...") })
	@GetMapping(value = "/ongoing")
	public ResponseEntity<?> findAllOngoingEvents() {
		return eventService.findAllOngoingEvents();
	}

	@Operation(summary = "To Fetch All COMPLETED Events", description = "This API will Fetch All COMPLETED the Events  Available in the Database Table...")
	@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "All the COMPLETED Events Found Successfully..."),
	@ApiResponse(responseCode = "404", description = "No COMPLETED Events Present in the Database Table...") })
	@GetMapping(value = "/completed")
	public ResponseEntity<?> findAllCompletedEvents() {
		return eventService.findAllCompletedEvents();
	}

	@Operation(summary = "To Fetch All DELETED Events", description = "This API will Fetch All DELETED the Events  Available in the Database Table...")
	@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "All the DELETED Events Found Successfully..."),
	@ApiResponse(responseCode = "404", description = "No DELETED Events Present in the Database Table...") })
	@GetMapping(value = "/deleted")
	public ResponseEntity<?> findAllDeletedEvents() {
		return eventService.findAllDeletedEvents();
	}

	@Operation(summary = "To Set Event Status  to ON_GOING", description = "This API will accept the Event ID as path variable and Set the EventStatus to ON_GOING...")
	@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Successfully updated Event status to ON_GOING..."),
	@ApiResponse(responseCode = "400", description = "Invalid Event ID... Unable to set Event status to ON-GOING...") })
	@PatchMapping("/ongoing/{id}")
	public ResponseEntity<?> setEventStatusToOngoing(@PathVariable int id) {
		return eventService.setEventStatusToOngoing(id);
	}

	@Operation(summary = "To Set Event Status  to COMPLETED", description = "This API will accept the Event ID as path variable and Set the EventStatus to COMPLETED...")
	@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Successfully updated Event status to COMPLETED..."),
	@ApiResponse(responseCode = "400", description = "Invalid Event ID... Unable to set Event status to COMPLETED...") })
	@PatchMapping("/completed/{id}")
	public ResponseEntity<?> setEventStatusToCompleted(@PathVariable int id) {
		return eventService.setEventStatusToCompleted(id);
	}

	@Operation(summary = "To Set Event Status  to DELETED", description = "This API will accept the Event ID as path variable and Set the EventStatus to DELETED...")
	@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Successfully updated Event status to DELETED..."),
	@ApiResponse(responseCode = "400", description = "Invalid Event ID... Unable to set Event status to DELETED...") })
//	@Hidden // to hide
	@PatchMapping("/deleted/{id}")
	public ResponseEntity<?> setEventStatusToDeleted(@PathVariable int id) {
		return eventService.setEventStatusToDeleted(id);
	}

	@Operation(summary = "To fetch All the Events in range...", description = "This API will Fetch all Events By Id in the given date range which are Available in the Database Table...")
	@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Events found successfully in a given range..."),
	@ApiResponse(responseCode = "400", description = "Bad request... No Events Present in the Database Table in a given range...") })
	@GetMapping(value = "/in-date")
	public ResponseEntity<?> findEventsBetweenDates(@RequestParam LocalDateTime fromDateTime,
			@RequestParam LocalDateTime toDateTime) {
		return eventService.findEventsBetweenDates(fromDateTime, toDateTime);
	}
	
}
