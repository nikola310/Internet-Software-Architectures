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

import com.management.dto.BidDTO;
import com.management.interfaces.BidManagerInterface;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
@RestController
@RequestMapping(value = "/bid")
public class BidController {

	private BidManagerInterface manager;

	@Autowired
	public BidController(BidManagerInterface manager) {
		this.manager = manager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<BidDTO>> getBids() {

		List<BidDTO> list = manager.ReadAll();

		return new ResponseEntity<List<BidDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<BidDTO> getBid(@PathVariable int id) {
		BidDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<BidDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<BidDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<BidDTO> addBid(@Validated @RequestBody BidDTO dto) {
		
		if (dto == null) {
			return new ResponseEntity<BidDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Create(dto);

		return new ResponseEntity<BidDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<BidDTO> updateBid(@Validated @RequestBody BidDTO dto) {
		if (dto == null) {
			return new ResponseEntity<BidDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Update(dto);

		return new ResponseEntity<BidDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<BidDTO> deleteBid(@PathVariable("id") int id) {
		if (!manager.Delete(id)) {
			return new ResponseEntity<BidDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<BidDTO>(HttpStatus.OK);
	}

}
