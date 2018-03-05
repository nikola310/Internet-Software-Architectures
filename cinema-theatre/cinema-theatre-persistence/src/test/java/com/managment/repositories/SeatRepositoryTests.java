package com.managment.repositories;

import java.util.ArrayList;
import java.util.Date;

import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.context.UnitOfWork;
import com.management.entities.Hall;
import com.management.entities.Seat;

/**
 * @author Zivko Stanisic
 *
 */
public class SeatRepositoryTests {
	private UnitOfWork uow;

	public SeatRepositoryTests() {
		uow = new UnitOfWork();
	}

	@Test
	public void CRUD_SeatTest() {
		Mockery mock = new Mockery();

		// Arrange
		int key = 0;
		Seat seat = new Seat();
		seat.setSeatTaken(false);
		seat.setSeatModified(new Date());

		Hall hall = new Hall();
		hall.setHallName("A1");

		seat.setHall(hall);

		// Act
		uow.getHallRepository().Add(hall);
		uow.getSeatRepository().Add(seat);
		uow.commitChanges();

		// Arrange and act
		ArrayList<Seat> list = uow.getSeatRepository().ReadAll();

		for (Seat tmp : list) {
			if (tmp.getHall().getHallName().equals("A1")
					&& !tmp.isSeatTaken()) {
				key = tmp.getSeatId();
				break;
			}
		}

		seat = uow.getSeatRepository().Read(key);

		// Assert
		Assert.assertNotNull(seat);

		Assert.assertEquals(seat.getHall().getHallName(), "A1");
		Assert.assertTrue(!seat.isSeatTaken());

		// Arrange
		seat = uow.getSeatRepository().Read(key);
		uow.commitChanges();

		Assert.assertNotNull(seat);

		seat.setSeatTaken(true);

		// Act
		uow.getSeatRepository().Update();
		uow.commitChanges();

		uow.getHallRepository().Delete(seat.getHall().getHallId());
		uow.getSeatRepository().Delete(key);
		uow.commitChanges();

		// Assert
		mock.assertIsSatisfied();
	}
}
