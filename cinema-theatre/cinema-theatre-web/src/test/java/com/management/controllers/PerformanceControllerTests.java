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

import com.management.dto.PerformanceDTO;
import com.management.entities.Performance;
import com.management.fake.PerformanceRepositoryFake;
import com.management.managers.PerformanceManager;
import com.management.repositories.PerformanceRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class PerformanceControllerTests {

	private PerformanceRepository performanceRepository;

	@Test
	public void AddingNewPerformance_ReturnsOK() {
		// Arrange
		performanceRepository = new PerformanceRepositoryFake();

		PerformanceDTO dto = new PerformanceDTO();
		dto.setPerCreationDate(new Date());
		dto.setPerDescription("Neki opis.");
		dto.setPerDirector("Nikola Stojanovic");
		dto.setPerDuration(11);
		dto.setPerGenre("Komedija");
		dto.setPerPoster(new byte[] { 121 });
		dto.setPerPrice(100);
		dto.setPerRank(0);
		dto.setPerType('M');
		PerformanceManager manager = new PerformanceManager(performanceRepository);
		PerformanceController controller = new PerformanceController(manager);

		// Act and assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.addPerformance(dto), new ResponseEntity<PerformanceDTO>(dto, HttpStatus.OK));
	}

	@Test
	public void DeletingPerformance_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		performanceRepository = mock.mock(PerformanceRepository.class);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(performanceRepository).delete(1);
			}
		});

		// Act and assert
		PerformanceManager manager = new PerformanceManager(performanceRepository);
		PerformanceController controller = new PerformanceController(manager);

		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.deletePerformance(1), new ResponseEntity<PerformanceDTO>(HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadPerformance_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		performanceRepository = mock.mock(PerformanceRepository.class);

		final Performance per = new Performance();
		per.setPerCreationDate(new Date());
		per.setPerDescription("Neki opis.");
		per.setPerDirector("Nikola Stojanovic");
		per.setPerDuration(11);
		per.setPerGenre("Komedija");
		per.setPerPoster(new byte[] { 121 });
		per.setPerPrice(100);
		per.setPerRank(0);
		per.setPerType('M');

		mock.checking(new Expectations() {
			{
				oneOf(performanceRepository).findOne(1);
				will(returnValue(per));
			}
		});

		PerformanceManager manager = new PerformanceManager(performanceRepository);
		PerformanceController controller = new PerformanceController(manager);

		// Act
		ResponseEntity<PerformanceDTO> response = controller.getPerformance(1);
		PerformanceDTO dto = response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<PerformanceDTO>(dto, HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllPerformances_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		performanceRepository = mock.mock(PerformanceRepository.class);

		final ArrayList<Performance> list = new ArrayList<Performance>();

		Performance per1 = new Performance();
		per1.setPerCreationDate(new Date());
		per1.setPerDescription("Neki opis.");
		per1.setPerDirector("Zivko Stanisic");
		per1.setPerDuration(11);
		per1.setPerGenre("Akcija");
		per1.setPerPoster(new byte[] { 121 });
		per1.setPerPrice(100);
		per1.setPerRank(10);
		per1.setPerType('M');

		Performance per2 = new Performance();
		per2.setPerCreationDate(new Date());
		per2.setPerDescription("Opet opis.");
		per2.setPerDirector("Nikola Stojanovic");
		per2.setPerDuration(13);
		per2.setPerGenre("Drama");
		per2.setPerPoster(new byte[] { 121 });
		per2.setPerPrice(0);
		per2.setPerRank(1);
		per2.setPerType('M');

		list.add(per1);
		list.add(per2);

		mock.checking(new Expectations() {
			{
				oneOf(performanceRepository).findAll();
				will(returnValue(list));
			}
		});

		PerformanceManager manager = new PerformanceManager(performanceRepository);
		PerformanceController controller = new PerformanceController(manager);

		// Act
		ResponseEntity<List<PerformanceDTO>> response = controller.getPerformances();
		ArrayList<PerformanceDTO> listDTO = (ArrayList<PerformanceDTO>) response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<List<PerformanceDTO>>(listDTO, HttpStatus.OK));

		mock.assertIsSatisfied();
	}
}
