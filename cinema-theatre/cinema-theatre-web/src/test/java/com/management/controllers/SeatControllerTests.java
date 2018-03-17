package com.management.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.dto.SeatDTO;
import com.management.entities.Seat;
import com.management.fake.SeatRepositoryFake;
import com.management.managers.SeatManager;
import com.management.repositories.SeatRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class SeatControllerTests {

	private SeatRepository seatRepository;

	@Test
	public void AddingNewSeat_ReturnsOK() {
		// Arrange
		seatRepository = new SeatRepositoryFake();

		SeatDTO dto = new SeatDTO();
		dto.setSeatModified(new Date());
		dto.setSeatTaken(true);
		SeatManager manager = new SeatManager(seatRepository);
		SeatController controller = new SeatController(manager);

		// Act and assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.addSeat(dto), new ResponseEntity<SeatDTO>(dto, HttpStatus.OK));
	}

	@Test
	public void DeletingSeat_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		seatRepository = mock.mock(SeatRepository.class);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(seatRepository).delete(1);
			}
		});

		// Act and assert
		SeatManager manager = new SeatManager(seatRepository);
		SeatController controller = new SeatController(manager);

		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.deleteSeat(1), new ResponseEntity<SeatDTO>(HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadSeat_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		seatRepository = mock.mock(SeatRepository.class);

		final Seat seat = new Seat();
		seat.setSeatModified(new Date());
		seat.setSeatTaken(true);

		mock.checking(new Expectations() {
			{
				oneOf(seatRepository).findOne(1);
				will(returnValue(seat));
			}
		});

		SeatManager manager = new SeatManager(seatRepository);
		SeatController controller = new SeatController(manager);

		// Act
		ResponseEntity<SeatDTO> response = controller.getSeat(1);
		SeatDTO dto = response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<SeatDTO>(dto, HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllSeats_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		seatRepository = mock.mock(SeatRepository.class);

		final ArrayList<Seat> list = new ArrayList<Seat>();

		Seat seat1 = new Seat();
		seat1.setSeatModified(new Date());
		seat1.setSeatTaken(true);

		Seat seat2 = new Seat();
		seat2.setSeatModified(new Date());
		seat2.setSeatTaken(false);

		list.add(seat1);
		list.add(seat2);

		mock.checking(new Expectations() {
			{
				oneOf(seatRepository).findAll();
				will(returnValue(list));
			}
		});

		SeatManager manager = new SeatManager(seatRepository);
		SeatController controller = new SeatController(manager);

		// Act
		ResponseEntity<List<SeatDTO>> response = controller.getSeats();
		ArrayList<SeatDTO> listDTO = (ArrayList<SeatDTO>) response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<List<SeatDTO>>(listDTO, HttpStatus.OK));

		mock.assertIsSatisfied();
	}
}
