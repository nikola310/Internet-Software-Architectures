/**
 * 
 */
package com.management.controllers;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.dto.ReservationDTO;
import com.management.dto.SeatDTO;
import com.management.entities.Props;
import com.management.entities.Reservation;
import com.management.entities.User;
import com.management.fake.ReservationRepositoryFake;
import com.management.managers.ReservationManager;
import com.management.repositories.ReservationRepository;

/**
 * @author Nikola Stojanovic
 *
 */
public class ReservationControllerTests {

	private ReservationRepository repository;

	@Test
	public void AddingNewReservation_ReturnsOK() {
		// Arrange
		repository = new ReservationRepositoryFake();

		ReservationDTO dto = new ReservationDTO();
		dto.setPropsId(0);
		dto.setReservationId(0);
		dto.setUserId(0);
		ReservationManager manager = new ReservationManager(repository);
		ReservationController controller = new ReservationController(manager);

		// Act and assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.addReservation(dto),
				new ResponseEntity<ReservationDTO>(dto, HttpStatus.OK));
	}

	@Test
	public void DeletingReservation_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		repository = mock.mock(ReservationRepository.class);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(repository).delete(1);
			}
		});

		// Act and assert
		ReservationManager manager = new ReservationManager(repository);
		ReservationController controller = new ReservationController(manager);

		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.deleteReservation(1),
				new ResponseEntity<SeatDTO>(HttpStatus.OK));

		mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadReservation_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		repository = mock.mock(ReservationRepository.class);

		final Reservation rs = new Reservation();
		Props p = new Props();
		p.setPropsId(0);
		rs.setProps(p);
		User u = new User();
		u.setUserId(0);
		rs.setUser(u);

		mock.checking(new Expectations() {
			{
				oneOf(repository).findOne(1);
				will(returnValue(rs));
			}
		});

		ReservationManager manager = new ReservationManager(repository);
		ReservationController controller = new ReservationController(manager);

		// Act
		ResponseEntity<ReservationDTO> response = controller.getReservation(1);
		ReservationDTO dto = response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<ReservationDTO>(dto, HttpStatus.OK));

		mock.assertIsSatisfied();
	}
	
	@Test
	public void ReadAllReservations_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		repository = mock.mock(ReservationRepository.class);

		final ArrayList<Reservation> list = new ArrayList<Reservation>();

		Reservation r1 = new Reservation();
		Props props = new Props();
		props.setPropsId(0);
		r1.setProps(props);
		User user = new User();
		user.setUserId(0);
		r1.setUser(user);
		
		Reservation r2 = new Reservation();
		Props props2 = new Props();
		props2.setPropsId(1);
		r2.setProps(props2);
		User user2 = new User();
		user2.setUserId(1);
		r2.setUser(user2);

		list.add(r1);
		list.add(r2);

		mock.checking(new Expectations() {
			{
				oneOf(repository).findAll();
				will(returnValue(list));
			}
		});

		ReservationManager manager = new ReservationManager(repository);
		ReservationController controller = new ReservationController(manager);

		// Act
		ResponseEntity<List<ReservationDTO>> response = controller.getReservations();
		ArrayList<ReservationDTO> listDTO = (ArrayList<ReservationDTO>) response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<List<ReservationDTO>>(listDTO, HttpStatus.OK));

		mock.assertIsSatisfied();
	}
}
