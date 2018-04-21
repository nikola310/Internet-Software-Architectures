package com.management.controllers;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.dto.HallDTO;
import com.management.entities.Hall;
import com.management.fake.CinemaTheatreRepositoryFake;
import com.management.fake.HallRepositoryFake;
import com.management.fake.SeatRepositoryFake;
import com.management.managers.HallManager;
import com.management.repositories.HallRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class HallControllerTests {

	private HallRepository hallRepository;

	@Test
	public void AddingNewHall_ReturnsOK() {
		// Arrange
		hallRepository = new HallRepositoryFake();

		HallDTO dto = new HallDTO();
		dto.setHallName("A1");
		HallManager manager = new HallManager();
		manager.setCinemaTheatreRepository(new CinemaTheatreRepositoryFake());
		manager.setHallRepository(new HallRepositoryFake());
		manager.setSeatRepository(new SeatRepositoryFake());
		
		HallController controller = new HallController(manager);

		// Act and assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.addHall(dto), new ResponseEntity<HallDTO>(dto, HttpStatus.OK));
	}

	@Test
	public void DeletingHall_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		hallRepository = mock.mock(HallRepository.class);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(hallRepository).delete(1);
			}
		});

		// Act and assert
		HallManager manager = new HallManager();
		manager.setCinemaTheatreRepository(new CinemaTheatreRepositoryFake());
		manager.setHallRepository(hallRepository);
		manager.setSeatRepository(new SeatRepositoryFake());
		
		HallController controller = new HallController(manager);

		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.deleteHall(1), new ResponseEntity<HallDTO>(HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadHall_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		hallRepository = mock.mock(HallRepository.class);

		final Hall hall = new Hall();
		hall.setHallName("A1");

		mock.checking(new Expectations() {
			{
				oneOf(hallRepository).findOne(1);
				will(returnValue(hall));
			}
		});

		HallManager manager = new HallManager();
		manager.setCinemaTheatreRepository(new CinemaTheatreRepositoryFake());
		manager.setHallRepository(hallRepository);
		manager.setSeatRepository(new SeatRepositoryFake());
		
		HallController controller = new HallController(manager);

		// Act
		ResponseEntity<HallDTO> response = controller.getHall(1);
		HallDTO dto = response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<HallDTO>(dto, HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllHalls_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		hallRepository = mock.mock(HallRepository.class);

		final ArrayList<Hall> list = new ArrayList<Hall>();

		Hall hall1 = new Hall();
		hall1.setHallName("A1");

		Hall hall2 = new Hall();
		hall2.setHallName("A2");

		list.add(hall1);
		list.add(hall2);

		mock.checking(new Expectations() {
			{
				oneOf(hallRepository).findAll();
				will(returnValue(list));
			}
		});

		HallManager manager = new HallManager();
		manager.setCinemaTheatreRepository(new CinemaTheatreRepositoryFake());
		manager.setHallRepository(hallRepository);
		manager.setSeatRepository(new SeatRepositoryFake());
		
		HallController controller = new HallController(manager);

		// Act
		ResponseEntity<List<HallDTO>> response = controller.getHalls();
		ArrayList<HallDTO> listDTO = (ArrayList<HallDTO>) response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<List<HallDTO>>(listDTO, HttpStatus.OK));

		mock.assertIsSatisfied();
	}
}
