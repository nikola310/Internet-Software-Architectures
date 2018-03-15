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

import com.management.dto.HallDTO;
import com.management.interfaces.HallManagerInterface;

/**
 * @author Zivko Stanisic
 *
 */
@RestController
@RequestMapping(value = "/hall")
public class HallController {
	
	private HallManagerInterface manager;

	@Autowired
	public HallController(HallManagerInterface manager) {
		this.manager = manager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<HallDTO>> getHalls() {

		List<HallDTO> list = manager.ReadAll();

		return new ResponseEntity<List<HallDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<HallDTO> getHall(@PathVariable int id) {
		HallDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<HallDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<HallDTO>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HallDTO> addHall(@RequestBody HallDTO dto) {
		if (dto == null) {
			return new ResponseEntity<HallDTO>(HttpStatus.NOT_FOUND);
		}
		
		manager.Create(dto);
		
		return new ResponseEntity<HallDTO>(dto, HttpStatus.OK);	
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<HallDTO> updateHall(@RequestBody HallDTO dto) {
		if (dto == null) {
			return new ResponseEntity<HallDTO>(HttpStatus.NOT_FOUND);
		}
		
		manager.Update(dto);
		
		return new ResponseEntity<HallDTO>(dto, HttpStatus.OK);	
	}
}
