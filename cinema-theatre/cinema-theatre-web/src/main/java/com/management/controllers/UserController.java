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

import com.management.dto.UserDTO;
import com.management.interfaces.UserManagerInterface;

/**
 * @author Zivko Stanisic
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

	private UserManagerInterface manager;

	@Autowired
	public UserController(UserManagerInterface manager) {
		this.manager = manager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsers() {

		List<UserDTO> list = manager.ReadAll();

		return new ResponseEntity<List<UserDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUser(@PathVariable int id) {
		UserDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDTO> addUser(@Validated @RequestBody UserDTO dto) {
		if (dto == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Create(dto);

		return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> updateUser(@Validated @RequestBody UserDTO dto) {
		if (dto == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Update(dto);

		return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") int id) {
		if (!manager.Delete(id)) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserDTO>(HttpStatus.OK);
	}
}
