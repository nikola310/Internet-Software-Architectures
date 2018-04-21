package com.management.managers;

import java.util.ArrayList;
import java.util.Date;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.SeatDTO;
import com.management.entities.Seat;
import com.management.fake.SeatRepositoryFake;
import com.management.fake.UserRepositoryFake;
import com.management.repositories.SeatRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class SeatManagerTests {

	private SeatRepository seatRepository;

	@Test
	public void AddingNewSeat_ReturnsBoolean() {
		// Arrange
		seatRepository = new SeatRepositoryFake();
		UserRepositoryFake userRepository = new UserRepositoryFake();

		SeatDTO dto = new SeatDTO();
		dto.setSeatModified(new Date());
		SeatManager manager = new SeatManager();
		manager.setSeatRepository(seatRepository);
		manager.setUserRepository(userRepository);

		// Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));

		Seat seat = seatRepository.findOne(0);

		Assert.assertEquals(dto.getSeatId(), seat.getSeatId());
		Assert.assertEquals(dto.getSeatModified(), seat.getSeatModified());
	}

	@Test
	public void DeletingSeat_ReturnsBoolean() {
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
		SeatManager manager = new SeatManager();
		manager.setSeatRepository(seatRepository);
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Delete(1));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadSeat_ReturnsSeat() {
		// Arrange
		Mockery mock = new Mockery();
		seatRepository = mock.mock(SeatRepository.class);

		final Seat seat = new Seat();
		seat.setSeatModified(new Date());

		mock.checking(new Expectations() {
			{
				oneOf(seatRepository).findOne(1);
				will(returnValue(seat));
			}
		});

		SeatManager manager = new SeatManager();
		manager.setSeatRepository(seatRepository);

		// Act
		SeatDTO dto = manager.Read(1);

		// Assert
		Assert.assertNotNull(dto);

		Assert.assertEquals(dto.getSeatId(), seat.getSeatId());
		Assert.assertEquals(dto.getSeatModified(), seat.getSeatModified());

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllSeats_ReturnsAllSeats() {
		// Arrange
		Mockery mock = new Mockery();
		seatRepository = mock.mock(SeatRepository.class);

		final ArrayList<Seat> list = new ArrayList<Seat>();

		Seat seat1 = new Seat();
		seat1.setSeatModified(new Date());

		Seat seat2 = new Seat();
		seat2.setSeatModified(new Date());

		list.add(seat1);
		list.add(seat2);

		mock.checking(new Expectations() {
			{
				oneOf(seatRepository).findAll();
				will(returnValue(list));
			}
		});

		SeatManager manager = new SeatManager();
		manager.setSeatRepository(seatRepository);

		// Act
		ArrayList<SeatDTO> listDTO = manager.ReadAll();

		// Assert
		Assert.assertNotNull(listDTO);

		Assert.assertEquals(listDTO.get(0).getSeatId(), list.get(0).getSeatId());
		Assert.assertEquals(listDTO.get(0).getSeatModified(), list.get(0).getSeatModified());

		Assert.assertEquals(listDTO.get(1).getSeatId(), list.get(1).getSeatId());
		Assert.assertEquals(listDTO.get(1).getSeatModified(), list.get(1).getSeatModified());

		mock.assertIsSatisfied();
	}
}
