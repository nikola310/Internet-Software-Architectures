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

import com.management.dto.RankscaleDTO;
import com.management.interfaces.RankscaleManagerInterface;

/**
 * @author Nikola Stojanovic
 *
 */
@RestController
@RequestMapping(value = "/rankscale")
public class RankscaleController {

	private RankscaleManagerInterface manager;

	@Autowired
	public RankscaleController(RankscaleManagerInterface manager) {
		this.manager = manager;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RankscaleDTO>> getRankscales() {
		List<RankscaleDTO> list = manager.ReadAll();

		return new ResponseEntity<List<RankscaleDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RankscaleDTO> getRankscale(@PathVariable int id) {
		RankscaleDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<RankscaleDTO>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<RankscaleDTO>(dto, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<RankscaleDTO> addRankscale(
			@Validated @RequestBody RankscaleDTO dto) {
		if (dto == null) {
			return new ResponseEntity<RankscaleDTO>(HttpStatus.NOT_FOUND);
		} else {
			manager.Create(dto);
			return new ResponseEntity<RankscaleDTO>(dto, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<RankscaleDTO> updateRankscale(@RequestBody RankscaleDTO dto) {
		if (dto == null) {
			return new ResponseEntity<RankscaleDTO>(HttpStatus.NOT_FOUND);
		} else {
			manager.Update(dto);
			return new ResponseEntity<RankscaleDTO>(dto, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<RankscaleDTO> deleteRankscale(@PathVariable("id") int id) {
		if (!manager.Delete(id)) {
			return new ResponseEntity<RankscaleDTO>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<RankscaleDTO>(HttpStatus.OK);
		}
	}
}
