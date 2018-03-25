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

import com.management.dto.ActorDTO;
import com.management.interfaces.ActorManagerInterface;

/**
 * @author Zivko Stanisic
 *
 */
@RestController
@RequestMapping(value = "/actor")
public class ActorController {

	private ActorManagerInterface manager;

	@Autowired
	public ActorController(ActorManagerInterface manager) {
		this.manager = manager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ActorDTO>> getActors() {

		List<ActorDTO> list = manager.ReadAll();

		return new ResponseEntity<List<ActorDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ActorDTO> getActor(@PathVariable("id") int id) {
		ActorDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<ActorDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ActorDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ActorDTO> addActor(@Validated @RequestBody ActorDTO dto) {
		if (dto == null) {
			return new ResponseEntity<ActorDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Create(dto);

		return new ResponseEntity<ActorDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ActorDTO> updateActor(@Validated @RequestBody ActorDTO dto) {
		if (dto == null) {
			return new ResponseEntity<ActorDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Update(dto);

		return new ResponseEntity<ActorDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ActorDTO> deleteActor(@PathVariable("id") int id) {
		if (!manager.Delete(id)) {
			return new ResponseEntity<ActorDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ActorDTO>(HttpStatus.OK);
	}
}
