package com.managment.repositories;

import java.util.ArrayList;

import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.context.UnitOfWork;
import com.management.entities.CinemaTheatre;

/**
 * @author Zivko Stanisic
 *
 */
public class CinemaTheatreRepositoryTests {

	private UnitOfWork uow;

	public CinemaTheatreRepositoryTests() {
		uow = new UnitOfWork();
	}

	@Test
	public void AddingNewCinemaTheatreTest() {
		Mockery mock = new Mockery();

		// Arrange
		int key = 0;
		CinemaTheatre cinemaTheatre = new CinemaTheatre();
		cinemaTheatre.setCtAdress("Bulevar oslobodjenja 11");
		cinemaTheatre.setCtDescription("Ovo je opis.");
		cinemaTheatre.setCtName("Bioskop");
		cinemaTheatre.setCtPhone(123456789);
		cinemaTheatre.setCtStateid("381");

		// Act
		uow.getCinemaTheatreRepository().Add(cinemaTheatre);
		uow.commitChanges();

		// Arrange and act
		cinemaTheatre = uow.getCinemaTheatreRepository().Read(1);
		uow.commitChanges();

		// Assert
		Assert.assertNotNull(cinemaTheatre);

		Assert.assertEquals("Bulevar oslobodjenja 11", cinemaTheatre.getCtAdress());
		Assert.assertEquals("Ovo je opis.", cinemaTheatre.getCtDescription());
		Assert.assertEquals("Bioskop", cinemaTheatre.getCtName());
		Assert.assertEquals(new Integer(1234567890), cinemaTheatre.getCtPhone());
		Assert.assertEquals("381", cinemaTheatre.getCtStateid());

		// Arrange
		ArrayList<CinemaTheatre> list = uow.getCinemaTheatreRepository().ReadAll();
		for (CinemaTheatre tmp : list) {
			if (tmp.getCtName().equals("Bioskop") && tmp.getCtDescription().equals("Ovo je opis.")
					&& tmp.getCtAdress().equals("Bulevar oslobodjenja 11")) {
				key = tmp.getCtId();
				break;
			}
		}

		cinemaTheatre = uow.getCinemaTheatreRepository().Read(key);
		uow.commitChanges();

		Assert.assertNotNull(cinemaTheatre);

		cinemaTheatre.setCtDescription("Promena.");

		// Act
		uow.getCinemaTheatreRepository().Update();
		uow.commitChanges();

		uow.getCinemaTheatreRepository().Delete(key);
		uow.commitChanges();

		// Assert
		mock.assertIsSatisfied();
	}
}
