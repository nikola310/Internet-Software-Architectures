package com.managment.repositories;

import java.util.ArrayList;

import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.context.UnitOfWork;
import com.management.entities.CinemaTheatre;
import com.management.entities.Hall;

/**
 * @author Zivko Stanisic
 *
 */
public class HallRepositoryTests {
	private UnitOfWork uow;

	public HallRepositoryTests() {
		uow = new UnitOfWork();
	}

	@Test
	public void CRUD_HallTest() {
		Mockery mock = new Mockery();

		// Arrange
		int key = 0;
		Hall hall = new Hall();
		hall.setHallName("A1");
		
		CinemaTheatre cinemaTheatre = new CinemaTheatre();
		cinemaTheatre.setCtAdress("Bulevar oslobodjenja 11");
		cinemaTheatre.setCtDescription("Ovo je opis.");
		cinemaTheatre.setCtName("Bioskop");
		cinemaTheatre.setCtPhone(123456789);
		cinemaTheatre.setCtStateid("381");

		hall.setCinemaTheatre(cinemaTheatre);

		// Act
		uow.getCinemaTheatreRepository().Add(cinemaTheatre);
		uow.getHallRepository().Add(hall);
		uow.commitChanges();

		// Arrange and act
		ArrayList<Hall> list = uow.getHallRepository().ReadAll();

		for (Hall tmp : list) {
			if (tmp.getHallName().equals("A1")) {
				key = tmp.getHallId();
				break;
			}
		}

		hall = uow.getHallRepository().Read(key);

		// Assert
		Assert.assertNotNull(hall);

		Assert.assertEquals(hall.getHallName(), "A1");
		Assert.assertEquals("Bulevar oslobodjenja 11", hall.getCinemaTheatre().getCtAdress());
		Assert.assertEquals("Ovo je opis.", hall.getCinemaTheatre().getCtDescription());
		Assert.assertEquals("Bioskop", hall.getCinemaTheatre().getCtName());
		Assert.assertEquals(new Integer(1234567890), hall.getCinemaTheatre().getCtPhone());
		Assert.assertEquals("381", hall.getCinemaTheatre().getCtStateid());

		// Arrange
		hall = uow.getHallRepository().Read(key);
		uow.commitChanges();

		Assert.assertNotNull(hall);

		hall.setHallName("A2");

		// Act
		uow.getHallRepository().Update();
		uow.commitChanges();

		uow.getHallRepository().Delete(key);
		uow.getCinemaTheatreRepository().Delete(hall.getCinemaTheatre().getCtId());
		uow.commitChanges();

		// Assert
		mock.assertIsSatisfied();
	}
}
