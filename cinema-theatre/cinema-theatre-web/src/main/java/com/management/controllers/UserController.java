package com.management.controllers;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.management.dto.LoginDTO;
import com.management.dto.ProfileDTO;
import com.management.dto.RegistrationDTO;
import com.management.dto.UserDTO;
import com.management.entities.User;
import com.management.interfaces.UserManagerInterface;
import com.management.mail.MailingInterface;

/**
 * @author Zivko Stanisic
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserManagerInterface manager;

	@Autowired
	private MailingInterface mailingManager;

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

	@RequestMapping(value = "/confirmation/{token}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getConfirmation(@PathVariable String token) {
		if (manager.Confirmation(token)) {
			return new ResponseEntity<UserDTO>(HttpStatus.OK);
		}

		return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<RegistrationDTO> addUser(
			@Validated @RequestBody RegistrationDTO dto) {

		try {
			if (dto == null) {
				return new ResponseEntity<RegistrationDTO>(dto,
						HttpStatus.NOT_FOUND);
			}
			String token = UUID.randomUUID().toString();

			mailingManager.sendRegistration(dto.getUserEmail(), token);
			manager.Create(dto, token);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RegistrationDTO>(dto,
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RegistrationDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LoginDTO> login(@Validated @RequestBody LoginDTO dto,
			@Context HttpServletRequest request) {
		try {
			if (dto == null) {
				return new ResponseEntity<LoginDTO>(dto, HttpStatus.NOT_FOUND);
			}

			if (!manager.Login(dto)) {
				System.err.println("Test");
				return new ResponseEntity<LoginDTO>(dto, HttpStatus.NOT_FOUND);
			}

			request.getSession().setAttribute("user", dto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<LoginDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ResponseEntity<ProfileDTO> profile(
			@Context HttpServletRequest request) {
		LoginDTO sessionDTO = (LoginDTO) request.getSession().getAttribute(
				"user");
		ProfileDTO dto = manager.ReadProfile(sessionDTO);

		if (dto == null) {
			return new ResponseEntity<ProfileDTO>(dto, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProfileDTO>(dto, HttpStatus.OK);

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<RegistrationDTO> edit(
			@Validated @RequestBody RegistrationDTO dto) {

		try {
			if (dto == null) {
				return new ResponseEntity<RegistrationDTO>(dto,
						HttpStatus.NOT_FOUND);
			}

			if (!manager.Edit(dto)) {
				return new ResponseEntity<RegistrationDTO>(dto,
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RegistrationDTO>(dto,
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RegistrationDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> updateUser(
			@Validated @RequestBody UserDTO dto) {
		if (dto == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}

		if (manager.Update(dto)) {

			return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
		}

		return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") int id) {
		if (!manager.Delete(id)) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserDTO>(HttpStatus.OK);
	}

	@RequestMapping(value = "/normal/{ID}", method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> setUserAsNormal(@PathVariable("ID") int ID,
			@Context HttpServletRequest request) {
		LoginDTO lg = (LoginDTO) request.getSession().getAttribute("user");
		if (lg == null)
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);

		User u = manager.getUser(lg);
		if (u == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		} else if (u.getUserAdmin() != 'S') {
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}

		UserDTO dto = manager.Read(ID);
		if (dto.equals(null))
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);

		dto.setUserAdmin('N');
		manager.Update(dto);
		return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/fanzone/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> setFZAdmin(@PathVariable("id") int id,
			@Context HttpServletRequest request) {
		LoginDTO lg = (LoginDTO) request.getSession().getAttribute("user");
		if (lg == null)
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);

		User u = manager.getUser(lg);
		if (u == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		} else if (u.getUserAdmin() != 'S') {
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}

		UserDTO dto = manager.Read(id);
		if (dto.equals(null))
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);

		dto.setUserAdmin('F');
		manager.Update(dto);
		return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/ct/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> setCTAdmin(@PathVariable("id") int id,
			@Context HttpServletRequest request) {
		LoginDTO lg = (LoginDTO) request.getSession().getAttribute("user");
		if (lg == null)
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);

		User u = manager.getUser(lg);
		if (u == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		} else if (u.getUserAdmin() != 'S') {
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}

		UserDTO dto = manager.Read(id);
		if (dto.equals(null))
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);

		dto.setUserAdmin('C');
		manager.Update(dto);
		return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/system/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> setSysAdmin(@PathVariable("id") int id,
			@Context HttpServletRequest request) {
		LoginDTO lg = (LoginDTO) request.getSession().getAttribute("user");
		if (lg == null)
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);

		User u = manager.getUser(lg);
		if (u == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		} else if (u.getUserAdmin() != 'S') {
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}

		UserDTO dto = manager.Read(id);
		if (dto.equals(null))
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);

		dto.setUserAdmin('S');
		manager.Update(dto);
		return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
	}

	public UserManagerInterface getManager() {
		return manager;
	}

	public void setManager(UserManagerInterface manager) {
		this.manager = manager;
	}

	public MailingInterface getMailingManager() {
		return mailingManager;
	}

	public void setMailingManager(MailingInterface mailingManager) {
		this.mailingManager = mailingManager;
	}
}
