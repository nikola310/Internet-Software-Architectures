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

import com.management.dto.ActorPerformancesDTO;
import com.management.interfaces.ActorPerformancesManagerInterface;

/**
 * @author Zivko Stanisic
 *
 */
@RestController
@RequestMapping(value = "/actor-performances")
public class ActorPerformancesController {

	private ActorPerformancesManagerInterface manager;

	@Autowired
	public ActorPerformancesController(ActorPerformancesManagerInterface manager) {
		this.manager = manager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ActorPerformancesDTO>> getActorPerformances() {

		List<ActorPerformancesDTO> list = manager.ReadAll();

		return new ResponseEntity<List<ActorPerformancesDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ActorPerformancesDTO> getActorPerformances(@PathVariable int id) {
		ActorPerformancesDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<ActorPerformancesDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ActorPerformancesDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ActorPerformancesDTO> addActorPerformances(@Validated @RequestBody ActorPerformancesDTO dto) {
		if (dto == null) {
			return new ResponseEntity<ActorPerformancesDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Create(dto);

		return new ResponseEntity<ActorPerformancesDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ActorPerformancesDTO> updateActorPerformances(@Validated @RequestBody ActorPerformancesDTO dto) {
		if (dto == null) {
			return new ResponseEntity<ActorPerformancesDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Update(dto);

		return new ResponseEntity<ActorPerformancesDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ActorPerformancesDTO> deleteActorPerformances(@PathVariable("id") int id) {
		if (!manager.Delete(id)) {
			return new ResponseEntity<ActorPerformancesDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ActorPerformancesDTO>(HttpStatus.OK);
	}
}
