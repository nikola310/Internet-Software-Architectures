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

import com.management.dto.PerformanceDTO;
import com.management.interfaces.PerformanceManagerInterface;

/**
 * @author Zivko Stanisic
 *
 */
@RestController
@RequestMapping(value = "/management/performance")
public class PerformanceController {
	
	private PerformanceManagerInterface manager;
	
	@Autowired
	public PerformanceController(PerformanceManagerInterface manager) {
		this.manager = manager;
	}

	@RequestMapping(value="getPerformances", method = RequestMethod.GET)
	public ResponseEntity<List<PerformanceDTO>> getPerformances() {

		List<PerformanceDTO> list = manager.ReadAll();

		return new ResponseEntity<List<PerformanceDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PerformanceDTO> getPerformance(@PathVariable int id) {
		PerformanceDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<PerformanceDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<PerformanceDTO>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PerformanceDTO> addPerformance(@RequestBody PerformanceDTO dto) {
		if (dto == null) {
			return new ResponseEntity<PerformanceDTO>(HttpStatus.NOT_FOUND);
		}
		
		manager.Create(dto);
		
		return new ResponseEntity<PerformanceDTO>(dto, HttpStatus.OK);	
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<PerformanceDTO> updatePerformance(@RequestBody PerformanceDTO dto) {
		if (dto == null) {
			return new ResponseEntity<PerformanceDTO>(HttpStatus.NOT_FOUND);
		}
		
		manager.Update(dto);
		
		return new ResponseEntity<PerformanceDTO>(dto, HttpStatus.OK);	
	}
}
