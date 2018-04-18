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
import org.springframework.web.bind.annotation.RestController;

import com.management.dto.FriendslistDTO;
import com.management.dto.LoginDTO;
import com.management.dto.ProfileDTO;
import com.management.dto.UserBasicDTO;
import com.management.interfaces.FriendsListManagerInterface;
import com.management.mail.MailingInterface;

/**
 * @author Zivko Stanisic
 *
 */
@RestController
@RequestMapping(value = "/friends-list")
public class FriendsListController {

	@Autowired
	private FriendsListManagerInterface manager;

	@Autowired
	private MailingInterface mailManager;

	@RequestMapping(value = "/peoples", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<UserBasicDTO>> peoples(@Context HttpServletRequest request) {
		LoginDTO sessionDTO = (LoginDTO) request.getSession().getAttribute("user");

		ArrayList<UserBasicDTO> dto = manager.GetUserBasicInformation(sessionDTO.getEmail());

		if (dto == null) {
			return new ResponseEntity<ArrayList<UserBasicDTO>>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ArrayList<UserBasicDTO>>(dto, HttpStatus.OK);

	}

	@RequestMapping(value = "/is-friend/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> isFriend(@Context HttpServletRequest request, @PathVariable int id) {
		LoginDTO sessionDTO = (LoginDTO) request.getSession().getAttribute("user");

		if (!manager.IsFriend(sessionDTO.getEmail(), id)) {
			return new ResponseEntity<String>("false", HttpStatus.OK);
		}

		return new ResponseEntity<String>("true", HttpStatus.OK);
	}

	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProfileDTO> profile(@Context HttpServletRequest request, @PathVariable int id) {
		ProfileDTO dto = manager.GEtFriend(id);
		
		if (dto == null) {
			return new ResponseEntity<ProfileDTO>(dto, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProfileDTO>(dto, HttpStatus.OK);

	}

	@RequestMapping(value = "/add-friend/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> addFriend(@Context HttpServletRequest request, @PathVariable int id) {
		LoginDTO sessionDTO = (LoginDTO) request.getSession().getAttribute("user");

		if (!manager.AddFriend(sessionDTO.getEmail(), id)) {
			return new ResponseEntity<String>("false", HttpStatus.OK);
		}
		ProfileDTO friend = manager.GEtFriend(id);
		mailManager.sendFriendRequest(friend.getUserEmail(), friend.getUserName());
		return new ResponseEntity<String>("true", HttpStatus.OK);
	}

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
	public ResponseEntity<FriendslistDTO> addFriendslist(@Validated @RequestBody FriendslistDTO dto) {
		if (dto == null) {
			return new ResponseEntity<FriendslistDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Create(dto);

		return new ResponseEntity<FriendslistDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<FriendslistDTO> updateFriendslist(@Validated @RequestBody FriendslistDTO dto) {
		if (dto == null) {
			return new ResponseEntity<FriendslistDTO>(HttpStatus.NOT_FOUND);
		}

		manager.Update(dto);

		return new ResponseEntity<FriendslistDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<FriendslistDTO> deleteFriendsList(@PathVariable("id") int id) {
		if (!manager.Delete(id)) {
			return new ResponseEntity<FriendslistDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<FriendslistDTO>(HttpStatus.OK);
	}
}
