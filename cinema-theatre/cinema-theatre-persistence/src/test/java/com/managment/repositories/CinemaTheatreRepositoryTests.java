/**
 * 
 */
package com.managment.repositories;

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

		//Arrange
		CinemaTheatre cinemaTheatre = new CinemaTheatre();
		cinemaTheatre.setCtAdress("Bulevar oslobodjenja 11");
		cinemaTheatre.setCtDescription("Ovo je opis.");
		cinemaTheatre.setCtName("Bioskop");
		cinemaTheatre.setCtPhone(123456789);
		cinemaTheatre.setCtStateid("381");

		//Act
		uow.getCinemaTheatreRepository().Add(cinemaTheatre);
		uow.commitChanges();
		
		//Assert
		mock.assertIsSatisfied();
	}

	@Test
	public void ReadExistingCinemaTheatreTest() {
		Mockery mock = new Mockery();

		//Arrange and act
		CinemaTheatre cinemaTheatre = uow.getCinemaTheatreRepository().Read(1);
		uow.commitChanges();

		//Assert
		Assert.assertNotNull(cinemaTheatre);

		Assert.assertEquals("Bulevar oslobodjenja 11", cinemaTheatre.getCtAdress());
		Assert.assertEquals("Ovo je opis.", cinemaTheatre.getCtDescription());
		Assert.assertEquals("Bioskop", cinemaTheatre.getCtName());
		Assert.assertEquals("1234567890",cinemaTheatre.getCtPhone());
		
		mock.assertIsSatisfied();
	}

	@Test
	public void UpdateAndDeleteExistingCinemaTheatreTest() {
		Mockery mock = new Mockery();
		
		//Arrange
		CinemaTheatre cinemaTheatre = uow.getCinemaTheatreRepository().Read(1);
		uow.commitChanges();

		Assert.assertNotNull(cinemaTheatre);

		cinemaTheatre.setCtDescription("Promena.");

		//Act
		uow.getCinemaTheatreRepository().Update();
		uow.commitChanges();
		
		uow.getCinemaTheatreRepository().Delete(1);
		uow.commitChanges();

		//Assert
		mock.assertIsSatisfied();
	}
}
