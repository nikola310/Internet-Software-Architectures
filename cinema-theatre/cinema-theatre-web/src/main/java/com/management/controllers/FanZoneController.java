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

import com.management.dto.FanZoneDTO;
import com.management.interfaces.FanZoneManagerInterface;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
@RestController
@RequestMapping(value = "/fan-zone")
public class FanZoneController {

	@Autowired
	private FanZoneManagerInterface manager;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FanZoneDTO>> getFanZone() {
		List<FanZoneDTO> list = manager.ReadAll();

		return new ResponseEntity<List<FanZoneDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<FanZoneDTO> getFanZone(@PathVariable int id) {
		FanZoneDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<FanZoneDTO>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<FanZoneDTO>(dto, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<FanZoneDTO> addFanZone(@RequestBody FanZoneDTO dto) {
		if (dto == null) {
			return new ResponseEntity<FanZoneDTO>(HttpStatus.NOT_FOUND);
		} else {
			manager.Create(dto);
			return new ResponseEntity<FanZoneDTO>(dto, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<FanZoneDTO> updateFanZone(@RequestBody FanZoneDTO dto) {
		if (dto == null) {
			return new ResponseEntity<FanZoneDTO>(HttpStatus.NOT_FOUND);
		} else {
			manager.Update(dto);
			return new ResponseEntity<FanZoneDTO>(dto, HttpStatus.OK);
		}
	}
}
