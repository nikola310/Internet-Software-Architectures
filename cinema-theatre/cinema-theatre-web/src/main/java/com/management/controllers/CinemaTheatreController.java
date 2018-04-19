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
import com.management.dto.LoginDTO;
import com.management.entities.User;
import com.management.interfaces.CinemaTheatreManagerInterface;
import com.management.interfaces.UserManagerInterface;

/**
 * @author Zivko Stanisic
 *
 */
@RestController
@RequestMapping(value = "/cinema-theatre")
public class CinemaTheatreController {

	@Autowired
	private UserManagerInterface userManager;

	private CinemaTheatreManagerInterface manager;

	@RequestMapping(value = "/basic", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<CinemaTheatreBasicDTO>> peoples() {
		ArrayList<CinemaTheatreBasicDTO> dto = manager.GetAllCinemaTheatreBasicInformation();

		if (dto == null) {
			return new ResponseEntity<ArrayList<CinemaTheatreBasicDTO>>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ArrayList<CinemaTheatreBasicDTO>>(dto, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CinemaTheatreDTO>> getCinemaTheatres() {

		List<CinemaTheatreDTO> list = manager.ReadAll();

		return new ResponseEntity<List<CinemaTheatreDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CinemaTheatreDTO> getCinemaTheatre(
			@PathVariable int id) {
		CinemaTheatreDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<CinemaTheatreDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<CinemaTheatreDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CinemaTheatreDTO> addCinemaTheatre(
			@Validated @RequestBody CinemaTheatreDTO dto) {
		if (dto == null) {
			return new ResponseEntity<CinemaTheatreDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Create(dto);

		return new ResponseEntity<CinemaTheatreDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<CinemaTheatreDTO> updateCinemaTheatre(
			@Validated @RequestBody CinemaTheatreDTO dto) {
		if (dto == null) {
			return new ResponseEntity<CinemaTheatreDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Update(dto);

		return new ResponseEntity<CinemaTheatreDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CinemaTheatreDTO> deleteCinemaTheatre(
			@PathVariable("id") int id) {
		if (!manager.Delete(id)) {
			return new ResponseEntity<CinemaTheatreDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<CinemaTheatreDTO>(HttpStatus.OK);
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity<String> newCinemaTheatre(
			@Validated @RequestBody CinemaTheatreDTO dto,
			@Context HttpServletRequest request) {
		if (dto == null) {
			return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);
		}
		LoginDTO lg = (LoginDTO) request.getSession().getAttribute("user");
		if (lg == null) {
			return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);
		}
		User u = userManager.getUser(lg);
		if (u == null) {
			return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);
		} else if (u.getUserAdmin() != 'S') {
			return new ResponseEntity<String>("Error", HttpStatus.FORBIDDEN);
		}

		manager.Create(dto);

		return new ResponseEntity<String>("Cinema-theatre added successfully",
				HttpStatus.OK);
	}
}
