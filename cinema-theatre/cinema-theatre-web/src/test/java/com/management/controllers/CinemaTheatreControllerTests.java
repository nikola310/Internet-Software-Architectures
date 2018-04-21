package com.management.controllers;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.dto.CinemaTheatreDTO;
import com.management.entities.CinemaTheatre;
import com.management.fake.CinemaTheatreRepositoryFake;
import com.management.managers.CinemaTheatreManager;
import com.management.repositories.CinemaTheatreRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class CinemaTheatreControllerTests {

	private CinemaTheatreRepository cinemaTheatreRepository;

	@Test
	public void AddingNewCinemaTheatre_ReturnsOK() {
		// Arrange
		cinemaTheatreRepository = new CinemaTheatreRepositoryFake();

		CinemaTheatreDTO dto = new CinemaTheatreDTO();
		dto.setCtAdress("Bulevar oslobodjenja 11");
		dto.setCtDescription("Ovo je opis.");
		dto.setCtName("Bioskop");
		dto.setCtPhone(1234567890);

		CinemaTheatreManager manager = new CinemaTheatreManager(cinemaTheatreRepository);
		CinemaTheatreController controller = new CinemaTheatreController();
		controller.setManager(manager);

		// Act and assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.addCinemaTheatre(dto), new ResponseEntity<CinemaTheatreDTO>(dto, HttpStatus.OK));

	}

	@Test
	public void DeletingCinemaTheatre_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		cinemaTheatreRepository = mock.mock(CinemaTheatreRepository.class);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(cinemaTheatreRepository).delete(1);
			}
		});

		// Act and assert
		CinemaTheatreManager manager = new CinemaTheatreManager(cinemaTheatreRepository);
		CinemaTheatreController controller = new CinemaTheatreController();
		controller.setManager(manager);

		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.deleteCinemaTheatre(1), new ResponseEntity<CinemaTheatreDTO>(HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadCinemaTheatre_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		cinemaTheatreRepository = mock.mock(CinemaTheatreRepository.class);

		final CinemaTheatre cinemaTheatre = new CinemaTheatre();
		cinemaTheatre.setCtAdress("Bulevar oslobodjenja 11");
		cinemaTheatre.setCtDescription("Ovo je opis.");
		cinemaTheatre.setCtName("Bioskop");
		cinemaTheatre.setCtPhone(1234567890);
		cinemaTheatre.setCtStateid("381");

		mock.checking(new Expectations() {
			{
				oneOf(cinemaTheatreRepository).findOne(1);
				will(returnValue(cinemaTheatre));
			}
		});

		CinemaTheatreManager manager = new CinemaTheatreManager(cinemaTheatreRepository);
		CinemaTheatreController controller = new CinemaTheatreController();
		controller.setManager(manager);

		// Act
		ResponseEntity<CinemaTheatreDTO> response = controller.getCinemaTheatre(1);
		CinemaTheatreDTO dto = response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<CinemaTheatreDTO>(dto, HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllCinemaTheatres_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		cinemaTheatreRepository = mock.mock(CinemaTheatreRepository.class);

		final ArrayList<CinemaTheatre> list = new ArrayList<CinemaTheatre>();

		CinemaTheatre cinemaTheatre1 = new CinemaTheatre();
		cinemaTheatre1.setCtAdress("Bulevar oslobodjenja 11");
		cinemaTheatre1.setCtDescription("Ovo je opis.");
		cinemaTheatre1.setCtName("Bioskop");
		cinemaTheatre1.setCtPhone(1234567890);
		cinemaTheatre1.setCtStateid("381");

		CinemaTheatre cinemaTheatre2 = new CinemaTheatre();
		cinemaTheatre2.setCtAdress("Bulevar oslobodjenja 9");
		cinemaTheatre2.setCtDescription("Takodje opis.");
		cinemaTheatre2.setCtName("Kazaliste");
		cinemaTheatre2.setCtPhone(987654321);
		cinemaTheatre2.setCtStateid("386");

		list.add(cinemaTheatre1);
		list.add(cinemaTheatre2);

		mock.checking(new Expectations() {
			{
				oneOf(cinemaTheatreRepository).findAll();
				will(returnValue(list));
			}
		});

		CinemaTheatreManager manager = new CinemaTheatreManager(cinemaTheatreRepository);
		CinemaTheatreController controller = new CinemaTheatreController();
		controller.setManager(manager);

		// Act
		ResponseEntity<List<CinemaTheatreDTO>> response = controller.getCinemaTheatres();
		ArrayList<CinemaTheatreDTO> listDTO = (ArrayList<CinemaTheatreDTO>) response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<List<CinemaTheatreDTO>>(listDTO, HttpStatus.OK));

		mock.assertIsSatisfied();
	}
}
