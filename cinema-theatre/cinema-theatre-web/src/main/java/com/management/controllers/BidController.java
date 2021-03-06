package com.management.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.dto.BidDTO;
import com.management.dto.LoginDTO;
import com.management.dto.OfferDTO;
import com.management.dto.PropsDTO;
import com.management.entities.User;
import com.management.interfaces.BidManagerInterface;
import com.management.interfaces.PropsManagerInterface;
import com.management.interfaces.UserManagerInterface;
import com.management.mail.MailingInterface;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
@RestController
@RequestMapping(value = "/bid")
public class BidController {

	@Autowired
	private MailingInterface mailingManager;

	@Autowired
	private PropsManagerInterface propsManager;

	@Autowired
	private UserManagerInterface userManager;

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

	@RequestMapping(value = "/byuser", method = RequestMethod.GET)
	public ResponseEntity<List<BidDTO>> getForUser(
			@Context HttpServletRequest request) {
		LoginDTO log = (LoginDTO) request.getSession().getAttribute("user");

		if (log == null)
			return new ResponseEntity<List<BidDTO>>(HttpStatus.NOT_FOUND);

		User user = userManager.getUser(log);

		if (user == null)
			return new ResponseEntity<List<BidDTO>>(HttpStatus.NOT_FOUND);

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

	@RequestMapping(value = "/set/{id}", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> setBid(@PathVariable("id") int id,
			@Context HttpServletRequest request) {
		if (request.getSession().getAttribute("user") == null) {
			return new ResponseEntity<String>("Error handling",
					HttpStatus.NOT_FOUND);
		}
		BidDTO dto = manager.Read(id);
		if (dto == null) {
			return new ResponseEntity<String>("Error handling",
					HttpStatus.NOT_FOUND);
		}
		request.getSession().setAttribute("bid", dto);
		return new ResponseEntity<String>("redirect:/editbid.html",
				HttpStatus.OK);
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

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity<String> newBid(@Validated @RequestBody BidDTO dto,
			@Context HttpServletRequest request) {
		if (dto == null) {
			return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);
		}

		LoginDTO lg = (LoginDTO) request.getSession().getAttribute("user");

		if (lg == null)
			return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);

		User u = userManager.getUser(lg);
		if (u == null)
			return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);

		dto.setUserId(u.getUserId());
		manager.Create(dto);

		return new ResponseEntity<String>("All ok", HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<String> deleteCurrentBid(@RequestParam("id") int id,
			@Context HttpServletRequest request) {

		request.getSession().removeAttribute("bid");
		if (!manager.Delete(id)) {
			System.out.println("usao u if!");
			return new ResponseEntity<String>("Could not delete bid.",
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Bid succesfully deleted.",
				HttpStatus.OK);
	}

	@RequestMapping(value = "set/price", method = RequestMethod.POST)
	public ResponseEntity<String> setPrice(@RequestParam("price") int price,
			@Context HttpServletRequest request) {
		if (price == 0)
			return new ResponseEntity<String>("Value not allowed",
					HttpStatus.NOT_FOUND);

		LoginDTO lg = (LoginDTO) request.getSession().getAttribute("user");
		if (lg == null)
			return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);

		User u = userManager.getUser(lg);
		if (u == null)
			return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);

		BidDTO bid = (BidDTO) request.getSession().getAttribute("bid");
		bid.setBidPrice(price);
		manager.Update(bid);
		request.getSession().setAttribute("bid", bid);
		return new ResponseEntity<String>("Success!", HttpStatus.OK);
	}

	@RequestMapping(value = "/offers", method = RequestMethod.GET)
	public ResponseEntity<List<OfferDTO>> getOffersForYourBids(
			@Context HttpServletRequest request) {
		LoginDTO log = (LoginDTO) request.getSession().getAttribute("user");

		if (log == null)
			return new ResponseEntity<List<OfferDTO>>(HttpStatus.NOT_FOUND);

		User user = userManager.getUser(log);

		if (user == null)
			return new ResponseEntity<List<OfferDTO>>(HttpStatus.NOT_FOUND);

		List<PropsDTO> propses = propsManager.getPropsByUser(user.getUserId());
		ArrayList<BidDTO> tmp = new ArrayList<BidDTO>();
		for (PropsDTO dto : propses) {
			if (new Date().compareTo(dto.getPropsDeadline()) < 0) {
				tmp.addAll(manager.readBidsByProps(dto.getPropsId()));
			}
		}

		ArrayList<OfferDTO> list = new ArrayList<OfferDTO>();
		for (BidDTO dto : tmp) {
			list.add(new OfferDTO(dto.getBidId(), dto.getBidPrice(),
					propsManager.Read(dto.getPropsId()).getPropsName()));
		}

		try {
			return new ResponseEntity<List<OfferDTO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<OfferDTO>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public ResponseEntity<String> acceptBid(@RequestParam("bidId") int bidId,
			@Context HttpServletRequest request) {
		LoginDTO log = (LoginDTO) request.getSession().getAttribute("user");

		if (log == null)
			return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);

		User user = userManager.getUser(log);

		if (user == null)
			return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);

		List<PropsDTO> propses = propsManager.getPropsByUser(user.getUserId());
		ArrayList<BidDTO> tmp = new ArrayList<BidDTO>();
		for (PropsDTO dto : propses) {
			if (new Date().compareTo(dto.getPropsDeadline()) < 0) {
				tmp.addAll(manager.readBidsByProps(dto.getPropsId()));
			}
		}

		for (BidDTO dto : tmp) {
			if (dto.getBidId() == bidId) {
				manager.acceptBid(dto);

				PropsDTO pDto = propsManager.Read(dto.getPropsId());
				pDto.setPropsApproved(false);
				propsManager.Update(pDto);

				mailingManager.sendBidAcceptedNotification(
						userManager.Read(dto.getUserId()).getUserEmail(),
						propsManager.Read(dto.getPropsId()).getPropsName());
			} else {
				manager.rejectBid(dto);
				mailingManager.sendBidRejectedNotification(
						userManager.Read(dto.getUserId()).getUserEmail(),
						propsManager.Read(dto.getPropsId()).getPropsName());
			}
		}

		return new ResponseEntity<String>("All ok", HttpStatus.OK);
	}

	@RequestMapping(value = "/reject", method = RequestMethod.POST)
	public ResponseEntity<String> rejectBid(@RequestParam("bidId") int bidId,
			@Context HttpServletRequest request) {
		LoginDTO log = (LoginDTO) request.getSession().getAttribute("user");

		if (log == null)
			return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);

		User user = userManager.getUser(log);

		if (user == null)
			return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);

		return new ResponseEntity<String>("All ok", HttpStatus.OK);
	}
}
