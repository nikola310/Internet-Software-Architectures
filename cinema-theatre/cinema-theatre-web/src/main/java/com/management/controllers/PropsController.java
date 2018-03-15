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

import com.management.dto.PropsDTO;
import com.management.interfaces.PropsManagerInterface;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
@RestController
@RequestMapping(value = "/props")
public class PropsController {
	
	private PropsManagerInterface manager;

	@Autowired
	public PropsController(PropsManagerInterface manager) {
		this.manager = manager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PropsDTO>> getProps() {
		List<PropsDTO> list = manager.ReadAll();

		return new ResponseEntity<List<PropsDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PropsDTO> getProps(@PathVariable int id) {
		PropsDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<PropsDTO>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<PropsDTO>(dto, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PropsDTO> addProps(@RequestBody PropsDTO dto) {
		if (dto == null) {
			return new ResponseEntity<PropsDTO>(HttpStatus.NOT_FOUND);
		} else {
			manager.Create(dto);
			return new ResponseEntity<PropsDTO>(dto, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<PropsDTO> updateProps(@RequestBody PropsDTO dto) {
		if (dto == null) {
			return new ResponseEntity<PropsDTO>(HttpStatus.NOT_FOUND);
		} else {
			manager.Update(dto);
			return new ResponseEntity<PropsDTO>(dto, HttpStatus.OK);
		}
	}
}
