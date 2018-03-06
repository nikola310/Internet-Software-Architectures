package com.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.management.dto.CinemaTheatreDTO;
import com.management.interfaces.CinemaTheatreManagerInterface;

/**
 * @author Zivko Stanisic
 *
 */
@RestController
@RequestMapping(value = "/management/cinema-theatre")
public class CinemaTheatreController {
	
	private CinemaTheatreManagerInterface manager;
	
	@Autowired
	public CinemaTheatreController(CinemaTheatreManagerInterface manager) {
		this.manager = manager;
	}

	@RequestMapping(value="getCinemaTheatres", method = RequestMethod.GET)
	public ResponseEntity<List<CinemaTheatreDTO>> getCinemaTheatres() {

		List<CinemaTheatreDTO> list = manager.ReadAll();

		return new ResponseEntity<List<CinemaTheatreDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CinemaTheatreDTO> getCinemaTheatre(@PathVariable int id) {
		CinemaTheatreDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<CinemaTheatreDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<CinemaTheatreDTO>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CinemaTheatreDTO> addCinemaTheatre(@RequestBody CinemaTheatreDTO dto) {
		if (dto == null) {
			return new ResponseEntity<CinemaTheatreDTO>(HttpStatus.NOT_FOUND);
		}
		
		manager.Create(dto);
		
		return new ResponseEntity<CinemaTheatreDTO>(dto, HttpStatus.OK);	
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<CinemaTheatreDTO> updateCinemaTheatre(@RequestBody CinemaTheatreDTO dto) {
		if (dto == null) {
			return new ResponseEntity<CinemaTheatreDTO>(HttpStatus.NOT_FOUND);
		}
		
		manager.Update(dto);
		
		return new ResponseEntity<CinemaTheatreDTO>(dto, HttpStatus.OK);	
	}
}
