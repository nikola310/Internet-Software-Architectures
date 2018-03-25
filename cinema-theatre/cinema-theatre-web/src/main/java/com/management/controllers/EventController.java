package com.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.management.dto.EventDTO;
import com.management.interfaces.EventManagerInterface;

/**
 * @author Zivko Stanisic
 *
 */
@RestController
@RequestMapping(value = "/event")
public class EventController {

	private EventManagerInterface manager;

	@Autowired
	public EventController(EventManagerInterface manager) {
		this.manager = manager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EventDTO>> getEvents() {

		List<EventDTO> list = manager.ReadAll();

		return new ResponseEntity<List<EventDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EventDTO> getEvent(@PathVariable int id) {
		EventDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<EventDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<EventDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<EventDTO> addEvent(@Validated @RequestBody EventDTO dto) {
		if (dto == null) {
			return new ResponseEntity<EventDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Create(dto);

		return new ResponseEntity<EventDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<EventDTO> updateEvent(@Validated @RequestBody EventDTO dto) {
		if (dto == null) {
			return new ResponseEntity<EventDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Update(dto);

		return new ResponseEntity<EventDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<EventDTO> deleteEvent(@PathVariable("id") int id) {
		if (!manager.Delete(id)) {
			return new ResponseEntity<EventDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<EventDTO>(HttpStatus.OK);
	}
}
