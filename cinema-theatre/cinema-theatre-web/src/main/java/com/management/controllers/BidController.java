package com.management.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.management.dto.BidDTO;
import com.management.entities.User;
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

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<BidDTO>> getBidByName(@PathVariable("id") int id) {
		User user = new User();
		user.setUserId(id);
		try {
			List<BidDTO> list = manager.getBids(user);
			return new ResponseEntity<List<BidDTO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<BidDTO>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/byuser/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<BidDTO>> getForUser(@PathVariable("id") int id) {
		User user = new User();
		user.setUserId(id);
		try {
			List<BidDTO> list = manager.getNotAccepted(user);
			return new ResponseEntity<List<BidDTO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<BidDTO>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/not-accepted/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<BidDTO>> getNotAcceptedByProps(
			@PathVariable("id") int id) {
		try {
			List<BidDTO> list = manager.readBidsByProps(id);
			return new ResponseEntity<List<BidDTO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<BidDTO>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/set/{id}", method = RequestMethod.POST)
	public ResponseEntity<BidDTO> setBid(@PathVariable("id") int id,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		BidDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<BidDTO>(HttpStatus.NOT_FOUND);
		}
		request.getSession().setAttribute("bid", dto);
		return new ResponseEntity<BidDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public ResponseEntity<BidDTO> getCurrentBid(
			@Context HttpServletRequest request) {
		BidDTO dto = (BidDTO) request.getSession().getAttribute("bid");
		if (dto == null) {
			return new ResponseEntity<BidDTO>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<BidDTO>(dto, HttpStatus.OK);
		}
	}
}
