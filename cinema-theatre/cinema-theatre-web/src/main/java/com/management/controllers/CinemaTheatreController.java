package com.management.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.management.dto.CinemaTheatreBasicDTO;
import com.management.dto.CinemaTheatreDTO;
import com.management.dto.HallEventDTO;
import com.management.interfaces.CinemaTheatreManagerInterface;

/**
 * @author Zivko Stanisic
 *
 */
@RestController
@RequestMapping(value = "/cinema-theatre")
public class CinemaTheatreController {

	@Autowired
	private CinemaTheatreManagerInterface manager;

	@RequestMapping(value = "/basic", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<CinemaTheatreBasicDTO>> peoples() {
		ArrayList<CinemaTheatreBasicDTO> dto = manager.GetAllCinemaTheatreBasicInformation();

		if (dto == null) {
			return new ResponseEntity<ArrayList<CinemaTheatreBasicDTO>>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ArrayList<CinemaTheatreBasicDTO>>(dto, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/halls/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<HallEventDTO>> halls(@PathVariable int id) {
		ArrayList<HallEventDTO> dto = manager.GetAllHallEvents(id);

		if (dto == null) {
			return new ResponseEntity<ArrayList<HallEventDTO>>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ArrayList<HallEventDTO>>(dto, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET)
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

	@RequestMapping(value = "/theatres", method = RequestMethod.GET)
	public ResponseEntity<List<CinemaTheatreDTO>> getTheatres() {
		List<CinemaTheatreDTO> list = manager.ReadAllTheatres();
		if (list == null) {
			return new ResponseEntity<List<CinemaTheatreDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<CinemaTheatreDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/cinemas", method = RequestMethod.GET)
	public ResponseEntity<List<CinemaTheatreDTO>> getCinemas() {

		List<CinemaTheatreDTO> list = manager.ReadAllCinemas();
		if (list == null) {
			return new ResponseEntity<List<CinemaTheatreDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<CinemaTheatreDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/selected", method = RequestMethod.GET)
	public ResponseEntity<CinemaTheatreDTO> getSelected(@Context HttpServletRequest request) {
		CinemaTheatreDTO dto = (CinemaTheatreDTO) request.getSession().getAttribute("ctSelected");
		if (dto == null) {
			return new ResponseEntity<CinemaTheatreDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CinemaTheatreDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CinemaTheatreDTO> addCinemaTheatre(@Validated @RequestBody CinemaTheatreDTO dto) {
		if (dto == null) {
			return new ResponseEntity<CinemaTheatreDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Create(dto);

		return new ResponseEntity<CinemaTheatreDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/select", method = RequestMethod.POST)
	public ResponseEntity<CinemaTheatreDTO> selectCinemaTheatre(@PathVariable("id") int id,
			@Context HttpServletRequest request) {
		CinemaTheatreDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<CinemaTheatreDTO>(HttpStatus.NOT_FOUND);
		}
		request.getSession().setAttribute("ctSelected", dto);

		return new ResponseEntity<CinemaTheatreDTO>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<CinemaTheatreDTO> updateCinemaTheatre(@Validated @RequestBody CinemaTheatreDTO dto) {
		if (dto == null) {
			return new ResponseEntity<CinemaTheatreDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Update(dto);

		return new ResponseEntity<CinemaTheatreDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CinemaTheatreDTO> deleteCinemaTheatre(@PathVariable("id") int id) {
		if (!manager.Delete(id)) {
			return new ResponseEntity<CinemaTheatreDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<CinemaTheatreDTO>(HttpStatus.OK);
	}
}
