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

import com.management.dto.SeatDTO;
import com.management.dto.SeatTakenDTO;
import com.management.interfaces.SeatManagerInterface;
import com.management.mail.MailingInterface;

/**
 * @author Zivko Stanisic
 *
 */
@RestController
@RequestMapping(value = "/seat")
public class SeatController {

	private SeatManagerInterface manager;

	@Autowired
	public SeatController(SeatManagerInterface manager) {
		this.manager = manager;
	}
	
	@Autowired
	private MailingInterface mailingManager;
	
	@RequestMapping(value = "/take-seat", method = RequestMethod.POST)
	public ResponseEntity<SeatTakenDTO> takeSeat(@Validated @RequestBody SeatTakenDTO dto) {
		if (dto == null) {
			return new ResponseEntity<SeatTakenDTO>(HttpStatus.NOT_FOUND);
		}

		if (!manager.TakeSeat(dto)) {
			return new ResponseEntity<SeatTakenDTO>(HttpStatus.NOT_FOUND);
		}

		mailingManager.sendInvitation(dto.getEmail());
		return new ResponseEntity<SeatTakenDTO>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/leave-seat", method = RequestMethod.POST)
	public ResponseEntity<SeatTakenDTO> leaveSeat(@Validated @RequestBody SeatTakenDTO dto) {
		if (dto == null) {
			return new ResponseEntity<SeatTakenDTO>(HttpStatus.NOT_FOUND);
		}

		if (!manager.LeaveSeat(dto)) {
			return new ResponseEntity<SeatTakenDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<SeatTakenDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SeatDTO>> getSeats() {

		List<SeatDTO> list = manager.ReadAll();

		return new ResponseEntity<List<SeatDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<SeatDTO> getSeat(@PathVariable int id) {
		SeatDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<SeatDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<SeatDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<SeatDTO> addSeat(@Validated @RequestBody SeatDTO dto) {
		if (dto == null) {
			return new ResponseEntity<SeatDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Create(dto);

		return new ResponseEntity<SeatDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<SeatDTO> updateSeat(@Validated @RequestBody SeatDTO dto) {
		if (dto == null) {
			return new ResponseEntity<SeatDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Update(dto);

		return new ResponseEntity<SeatDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<SeatDTO> deleteSeat(@PathVariable("id") int id) {
		if (!manager.Delete(id)) {
			return new ResponseEntity<SeatDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<SeatDTO>(HttpStatus.OK);
	}
}
