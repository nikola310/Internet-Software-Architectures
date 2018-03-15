package com.management.managers;

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
	public void ReadEvent_ReturnsEvent() {
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
}