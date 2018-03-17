package com.management.controllers;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.dto.FanZoneDTO;
import com.management.entities.FanZone;
import com.management.fake.FanZoneRepositoryFake;
import com.management.managers.FanZoneManager;
import com.management.repositories.FanZoneRepository;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class FanZoneControllerTests {
	private FanZoneRepository fanZoneRepository;

	@Test
	public void AddingNewFanZone_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		fanZoneRepository = new FanZoneRepositoryFake();

		FanZoneDTO dto = new FanZoneDTO();
		dto.setFanZoneName("Spodermna fan zone");
		FanZoneManager manager = new FanZoneManager(fanZoneRepository);
		FanZoneController controller = new FanZoneController(manager);

		// Act and assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.addFanZone(dto),
				new ResponseEntity<FanZoneDTO>(dto, HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void DeletingFanZone_ReturnsOK() {
		Mockery mock = new Mockery();
		fanZoneRepository = mock.mock(FanZoneRepository.class);

		// Expectations
		mock.checking(new Expectations() {
			{
				oneOf(fanZoneRepository).delete(1);
			}
		});

		FanZoneManager manager = new FanZoneManager(fanZoneRepository);
		// Act and assert
		FanZoneController controller = new FanZoneController(manager);
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.deleteFanZone(1),
				new ResponseEntity<FanZoneDTO>(HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadFanZone_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		fanZoneRepository = mock.mock(FanZoneRepository.class);

		final FanZone fz = new FanZone();
		fz.setFanZoneName("Spoderman fan zone");

		mock.checking(new Expectations() {
			{
				oneOf(fanZoneRepository).findOne(1);
				will(returnValue(fz));
			}
		});

		FanZoneManager manager = new FanZoneManager(fanZoneRepository);
		FanZoneController controller = new FanZoneController(manager);

		// Act
		ResponseEntity<FanZoneDTO> response = controller.getFanZone(1);
		FanZoneDTO dto = response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<FanZoneDTO>(dto,
				HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllFanZones_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		fanZoneRepository = mock.mock(FanZoneRepository.class);

		final ArrayList<FanZone> list = new ArrayList<FanZone>();

		FanZone fz1 = new FanZone();
		fz1.setFanZoneName("Spoderman fan zone");

		FanZone fz2 = new FanZone();
		fz2.setFanZoneName("Captain Murica fan zone");

		list.add(fz1);
		list.add(fz2);

		mock.checking(new Expectations() {
			{
				oneOf(fanZoneRepository).findAll();
				will(returnValue(list));
			}
		});

		FanZoneManager manager = new FanZoneManager(fanZoneRepository);
		FanZoneController controller = new FanZoneController(manager);

		// Act
		ResponseEntity<List<FanZoneDTO>> response = controller.getFanZones();
		ArrayList<FanZoneDTO> dtoList = (ArrayList<FanZoneDTO>) response
				.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<List<FanZoneDTO>>(
				dtoList, HttpStatus.OK));

		mock.assertIsSatisfied();
	}
}
