package com.management.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.dto.CreatePropsDTO;
import com.management.dto.LoginDTO;
import com.management.dto.PropsDTO;
import com.management.entities.Props;
import com.management.entities.Reservation;
import com.management.entities.User;
import com.management.interfaces.PropsManagerInterface;
import com.management.interfaces.ReservationManagerInterface;
import com.management.interfaces.UserManagerInterface;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
@RestController
@RequestMapping(value = "/props")
public class PropsController {

	@Autowired
	private ReservationManagerInterface reservationManager;

	@Autowired
	private UserManagerInterface userManager;

	private PropsManagerInterface manager;

	@Autowired
	public PropsController(PropsManagerInterface manager) {
		this.manager = manager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PropsDTO>> getProps() {
		List<PropsDTO> list = manager.ReadAll();
		List<PropsDTO> retVal = new ArrayList<PropsDTO>();
		for(PropsDTO dto : list){
			if(dto.isPropsApproved() == null){
				continue;
			}else if(dto.isPropsApproved()){
				retVal.add(dto);
			}
		}
		return new ResponseEntity<List<PropsDTO>>(retVal, HttpStatus.OK);
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
	public ResponseEntity<PropsDTO> addProps(
			@Validated @RequestBody PropsDTO dto) {
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

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PropsDTO> deleteProps(@RequestParam("id") int id) {
		if (!manager.Delete(id)) {
			return new ResponseEntity<PropsDTO>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<PropsDTO>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PropsDTO> deleteProps(@PathVariable("id") int id,
			@Context HttpServletRequest request) {
		LoginDTO lg = (LoginDTO) request.getSession().getAttribute("user");
		if (lg == null)
			return new ResponseEntity<PropsDTO>(HttpStatus.NOT_FOUND);

		User u = userManager.getUser(lg);
		if (u == null) {
			return new ResponseEntity<PropsDTO>(HttpStatus.NOT_FOUND);
		} else if (u.getUserAdmin() != 'F') {
			return new ResponseEntity<PropsDTO>(HttpStatus.FORBIDDEN);
		}

		Props p = new Props();
		p.setPropsId(id);
		for (Reservation r : reservationManager.getByProps(p)) {
			reservationManager.Delete(r.getReservationId());
		}

		if (!manager.Delete(id)) {
			return new ResponseEntity<PropsDTO>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<PropsDTO>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/not-checked", method = RequestMethod.GET)
	public ResponseEntity<List<PropsDTO>> getNotChecked(
			@Context HttpServletRequest request) {
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("user");
		if (dto == null)
			return new ResponseEntity<List<PropsDTO>>(HttpStatus.NOT_FOUND);

		User u = userManager.getUser(dto);
		if (u == null)
			return new ResponseEntity<List<PropsDTO>>(HttpStatus.NOT_FOUND);

		if (u.getUserAdmin() != 'F') {
			return new ResponseEntity<List<PropsDTO>>(HttpStatus.FORBIDDEN);
		}

		List<PropsDTO> list = manager.getNullAllowedProps();

		return new ResponseEntity<List<PropsDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/approve/{id}", method = RequestMethod.PUT)
	public ResponseEntity<PropsDTO> approveProps(@PathVariable("id") int id,
			@Context HttpServletRequest request) {
		LoginDTO lg = (LoginDTO) request.getSession().getAttribute("user");
		if (lg == null)
			return new ResponseEntity<PropsDTO>(HttpStatus.NOT_FOUND);

		User u = userManager.getUser(lg);
		if (u == null)
			return new ResponseEntity<PropsDTO>(HttpStatus.NOT_FOUND);

		if (u.getUserAdmin() != 'F') {
			return new ResponseEntity<PropsDTO>(HttpStatus.FORBIDDEN);
		}

		PropsDTO dto = manager.Read(id);
		dto.setPropsApproved(true);
		manager.Update(dto);
		return new ResponseEntity<PropsDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/reject/{id}", method = RequestMethod.PUT)
	public ResponseEntity<PropsDTO> rejectProps(@PathVariable("id") int id,
			@Context HttpServletRequest request) {
		LoginDTO lg = (LoginDTO) request.getSession().getAttribute("user");
		if (lg == null)
			return new ResponseEntity<PropsDTO>(HttpStatus.NOT_FOUND);

		User u = userManager.getUser(lg);
		if (u == null)
			return new ResponseEntity<PropsDTO>(HttpStatus.NOT_FOUND);

		if (u.getUserAdmin() != 'F') {
			return new ResponseEntity<PropsDTO>(HttpStatus.FORBIDDEN);
		}

		PropsDTO dto = manager.Read(id);
		dto.setPropsApproved(false);
		manager.Update(dto);
		return new ResponseEntity<PropsDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/official", method = RequestMethod.GET)
	public ResponseEntity<List<PropsDTO>> getOfficial() {
		List<PropsDTO> list = manager.getOfficialProps();

		return new ResponseEntity<List<PropsDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/byuser/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<PropsDTO>> propsByUser(@PathVariable("id") int id) {
		List<PropsDTO> list = manager.getPropsByUser(id);

		return new ResponseEntity<List<PropsDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/used", method = RequestMethod.POST)
	public ResponseEntity<CreatePropsDTO> createUsed(
			@RequestBody CreatePropsDTO dto, @Context HttpServletRequest request) {
		if (dto == null) {
			return new ResponseEntity<CreatePropsDTO>(HttpStatus.NOT_FOUND);
		} else {
			LoginDTO lg = ((LoginDTO) request.getSession().getAttribute("user"));
			if (lg == null)
				return new ResponseEntity<CreatePropsDTO>(HttpStatus.NOT_FOUND);
			User u = userManager.getUser(lg);
			if (u == null) {
				return new ResponseEntity<CreatePropsDTO>(HttpStatus.NOT_FOUND);
			}
			PropsDTO tmp = new PropsDTO(dto.getPropsName(),
					dto.getPropsDeadline(), dto.getPropsPrice(), u.getUserId(),
					dto.getPropsImage(), dto.getPropsDesc());
			tmp.setPropsApproved(null);
			tmp.setPropsUsed(true);
			manager.Create(tmp);
			return new ResponseEntity<CreatePropsDTO>(dto, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/official", method = RequestMethod.POST)
	public ResponseEntity<String> createOfficial(
			@RequestBody CreatePropsDTO dto, @Context HttpServletRequest request) {
		if (dto == null) {
			return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);
		} else {
			LoginDTO lg = ((LoginDTO) request.getSession().getAttribute("user"));
			if (lg == null)
				return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);
			User u = userManager.getUser(lg);
			if (u == null) {
				return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);
			} else if (u.getUserAdmin() != 'F') {
				return new ResponseEntity<String>("Error", HttpStatus.FORBIDDEN);
			}

			PropsDTO tmp = new PropsDTO(dto.getPropsName(),
					dto.getPropsDeadline(), dto.getPropsPrice(), u.getUserId(),
					dto.getPropsImage(), dto.getPropsDesc());
			tmp.setPropsApproved(true);
			tmp.setPropsUsed(false);
			manager.Create(tmp);
			return new ResponseEntity<String>("Props created successfully",
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/set", method = RequestMethod.POST)
	public ResponseEntity<String> setCurrentProps(
			@RequestParam("propsId") int propsId,
			@Context HttpServletRequest request) {
		LoginDTO lg = ((LoginDTO) request.getSession().getAttribute("user"));
		if (lg == null)
			return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);
		User u = userManager.getUser(lg);
		if (u == null) {
			return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);
		} else if (u.getUserAdmin() != 'F') {
			return new ResponseEntity<String>("Error", HttpStatus.FORBIDDEN);
		}

		PropsDTO props = manager.Read(propsId);
		request.getSession().setAttribute("props", props);
		return new ResponseEntity<String>("All ok!", HttpStatus.OK);
	}

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public ResponseEntity<PropsDTO> getCurrent(
			@Context HttpServletRequest request) {
		LoginDTO lg = ((LoginDTO) request.getSession().getAttribute("user"));
		if (lg == null)
			return new ResponseEntity<PropsDTO>(HttpStatus.NOT_FOUND);
		User u = userManager.getUser(lg);
		if (u == null) {
			return new ResponseEntity<PropsDTO>(HttpStatus.NOT_FOUND);
		} else if (u.getUserAdmin() != 'F') {
			return new ResponseEntity<PropsDTO>(HttpStatus.FORBIDDEN);
		}

		PropsDTO dto = (PropsDTO) request.getSession().getAttribute("props");

		if (dto == null) {
			return new ResponseEntity<PropsDTO>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<PropsDTO>(dto, HttpStatus.OK);
		}
	}
}
