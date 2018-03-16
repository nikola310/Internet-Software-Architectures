package com.management.managers;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.FanZoneDTO;
import com.management.entities.FanZone;
import com.management.fake.FanZoneRepositoryFake;
import com.management.repositories.FanZoneRepository;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class FanZoneManagerTests {

	private FanZoneRepository fanZoneRepository;

	@Test
	public void AddingNewFanZone_ReturnsBoolean() {
		// Arrange
		fanZoneRepository = new FanZoneRepositoryFake();

		FanZoneDTO dto = new FanZoneDTO();
		dto.setFanZoneName("Spoderman Fan Zone");
		FanZoneManager manager = new FanZoneManager(fanZoneRepository);

		// Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));

		FanZone fz = fanZoneRepository.findOne(0);

		Assert.assertEquals(dto.getFanZoneId(), fz.getFanZoneId());
		Assert.assertEquals(dto.getFanZoneName(), dto.getFanZoneName());

	}

	@Test
	public void DeleteFanZone_ReturnsBoolean() {
		// Arrange
		Mockery mock = new Mockery();
		fanZoneRepository = mock.mock(FanZoneRepository.class);

		// Expectations
		mock.checking(new Expectations() {
			{
				oneOf(fanZoneRepository).delete(1);
			}
		});

		// Act and assert
		FanZoneManager manager = new FanZoneManager(fanZoneRepository);
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Delete(1));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadFanZone_ReturnsFanZone() {
		// Arrange
		Mockery mock = new Mockery();
		fanZoneRepository = mock.mock(FanZoneRepository.class);

		final FanZone fz = new FanZone();
		fz.setFanZoneName("Spoderman Fan Zone");

		mock.checking(new Expectations() {
			{
				oneOf(fanZoneRepository).findOne(1);
				will(returnValue(fz));
			}
		});

		FanZoneManager manager = new FanZoneManager(fanZoneRepository);

		// Act
		FanZoneDTO dto = manager.Read(1);

		// Assert
		Assert.assertNotNull(dto);

		Assert.assertEquals(dto.getFanZoneName(), fz.getFanZoneName());
	}

	@Test
	public void ReadAllFanZones_ReturnsAllFanZones() {
		// Arrange
		Mockery mock = new Mockery();
		fanZoneRepository = mock.mock(FanZoneRepository.class);

		final ArrayList<FanZone> list = new ArrayList<FanZone>();
		FanZone fz1 = new FanZone();
		fz1.setFanZoneName("Spoderman fan zone");

		FanZone fz2 = new FanZone();
		fz2.setFanZoneName("Captain Amurica fan zone");

		list.add(fz1);
		list.add(fz2);

		mock.checking(new Expectations() {
			{
				oneOf(fanZoneRepository).findAll();
				will(returnValue(list));
			}
		});

		FanZoneManager manager = new FanZoneManager(fanZoneRepository);

		// Act
		ArrayList<FanZoneDTO> dtoList = manager.ReadAll();

		// Assert
		Assert.assertNotNull(dtoList);

		Assert.assertEquals(dtoList.get(0).getFanZoneId(), list.get(0)
				.getFanZoneId());
		Assert.assertEquals(dtoList.get(0).getFanZoneName(), list.get(0)
				.getFanZoneName());
		Assert.assertEquals(dtoList.get(1).getFanZoneId(), list.get(1)
				.getFanZoneId());
		Assert.assertEquals(dtoList.get(1).getFanZoneName(), list.get(1)
				.getFanZoneName());

		mock.assertIsSatisfied();
	}
}