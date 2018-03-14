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

import com.management.dto.FriendslistDTO;
import com.management.interfaces.FriendsListManagerInterface;

/**
 * @author Zivko Stanisic
 *
 */
@RestController
@RequestMapping(value = "/friends-list")
public class FriendsListController {
	
	@Autowired
	private FriendsListManagerInterface manager;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FriendslistDTO>> getFriendslists() {

		List<FriendslistDTO> list = manager.ReadAll();

		return new ResponseEntity<List<FriendslistDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<FriendslistDTO> getFriendslist(@PathVariable int id) {
		FriendslistDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<FriendslistDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<FriendslistDTO>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<FriendslistDTO> addFriendslist(@RequestBody FriendslistDTO dto) {
		if (dto == null) {
			return new ResponseEntity<FriendslistDTO>(HttpStatus.NOT_FOUND);
		}
		
		manager.Create(dto);
		
		return new ResponseEntity<FriendslistDTO>(dto, HttpStatus.OK);	
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<FriendslistDTO> updateFriendslist(@RequestBody FriendslistDTO dto) {
		if (dto == null) {
			return new ResponseEntity<FriendslistDTO>(HttpStatus.NOT_FOUND);
		}
		
		manager.Update(dto);
		
		return new ResponseEntity<FriendslistDTO>(dto, HttpStatus.OK);	
	}
}
