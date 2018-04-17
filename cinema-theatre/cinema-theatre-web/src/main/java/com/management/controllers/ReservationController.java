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

import com.management.dto.ReservationDTO;
import com.management.interfaces.ReservationManagerInterface;

/**
 * @author Nikola Stojanovic
 *
 */
@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {

	private ReservationManagerInterface manager;

	@Autowired
	public ReservationController(ReservationManagerInterface manager) {
		this.manager = manager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ReservationDTO>> getReservations() {
		List<ReservationDTO> list = manager.ReadAll();

		return new ResponseEntity<List<ReservationDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ReservationDTO> getReservation(@PathVariable int id) {
		ReservationDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<ReservationDTO>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ReservationDTO>(dto, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ReservationDTO> addReservation(
			@Validated @RequestBody ReservationDTO dto) {
		if (dto == null) {
			return new ResponseEntity<ReservationDTO>(HttpStatus.NOT_FOUND);
		} else {
			manager.Create(dto);
			return new ResponseEntity<ReservationDTO>(dto, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ReservationDTO> updateReservation(@RequestBody ReservationDTO dto) {
		if (dto == null) {
			return new ResponseEntity<ReservationDTO>(HttpStatus.NOT_FOUND);
		} else {
			manager.Update(dto);
			return new ResponseEntity<ReservationDTO>(dto, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ReservationDTO> deleteReservation(@PathVariable("id") int id) {
		if (!manager.Delete(id)) {
			return new ResponseEntity<ReservationDTO>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ReservationDTO>(HttpStatus.OK);
		}
	}
}