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

import com.management.dto.HistoryDTO;
import com.management.interfaces.HistoryManagerInterface;

/**
 * @author Zivko Stanisic
 *
 */
@RestController
@RequestMapping(value = "/history")
public class HistoryController {

	private HistoryManagerInterface manager;

	@Autowired
	public HistoryController(HistoryManagerInterface manager) {
		this.manager = manager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<HistoryDTO>> getHistorys() {

		List<HistoryDTO> list = manager.ReadAll();

		return new ResponseEntity<List<HistoryDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<HistoryDTO> getHistory(@PathVariable int id) {
		HistoryDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<HistoryDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<HistoryDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HistoryDTO> addHistory(@Validated @RequestBody HistoryDTO dto) {
		if (dto == null) {
			return new ResponseEntity<HistoryDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Create(dto);

		return new ResponseEntity<HistoryDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<HistoryDTO> updateHistory(@Validated @RequestBody HistoryDTO dto) {
		if (dto == null) {
			return new ResponseEntity<HistoryDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Update(dto);

		return new ResponseEntity<HistoryDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<HistoryDTO> deleteHistory(@PathVariable("id") int id) {
		if (!manager.Delete(id)) {
			return new ResponseEntity<HistoryDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<HistoryDTO>(HttpStatus.OK);
	}
}
