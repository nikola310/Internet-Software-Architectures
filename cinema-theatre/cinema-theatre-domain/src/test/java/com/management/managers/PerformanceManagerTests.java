package com.management.managers;

import java.util.ArrayList;
import java.util.Date;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.PerformanceDTO;
import com.management.entities.Performance;
import com.management.fake.PerformanceRepositoryFake;
import com.management.repositories.PerformanceRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class PerformanceManagerTests {
	private PerformanceRepository performanceRepository;

	@Test
	public void AddingNewPerformance_ReturnsBoolean() {
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

		// Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));

		// Performance per = performanceRepository.getOne(0);

		/*
		 * Assert.assertEquals(dto.getPerId(), per.getPerId());
		 * Assert.assertEquals(per.getPerDescription(),
		 * dto.getPerDescription()); Assert.assertEquals(per.getPerDirector(),
		 * dto.getPerDirector()); Assert.assertEquals(per.getPerDuration(),
		 * dto.getPerDuration()); Assert.assertEquals(per.getPerGenre(),
		 * dto.getPerGenre()); Assert.assertEquals((int)per.getPerPrice(),
		 * (int)dto.getPerPrice()); Assert.assertEquals(per.getPerRank(),
		 * dto.getPerRank()); Assert.assertEquals(per.getPerType(),
		 * dto.getPerType());
		 */
	}

	@Test
	public void DeletingPerformance_ReturnsBoolean() {
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
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Delete(1));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadPerformance_ReturnsPerformance() {
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

		// Act
		PerformanceDTO dto = manager.Read(1);

		// Assert
		Assert.assertNotNull(dto);

		Assert.assertEquals(dto.getPerId(), per.getPerId());
		Assert.assertEquals(per.getPerDescription(), dto.getPerDescription());
		Assert.assertEquals(per.getPerDirector(), dto.getPerDirector());
		Assert.assertEquals(per.getPerDuration(), dto.getPerDuration());
		Assert.assertEquals(per.getPerGenre(), dto.getPerGenre());
		Assert.assertEquals((int) per.getPerPrice(), (int) dto.getPerPrice());
		Assert.assertEquals(per.getPerRank(), dto.getPerRank());
		Assert.assertEquals(per.getPerType(), dto.getPerType());

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllPerformances_ReturnsAllPerformances() {
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

		// Act
		ArrayList<PerformanceDTO> listDTO = manager.ReadAll();

		// Assert
		Assert.assertNotNull(listDTO);

		Assert.assertEquals(listDTO.get(0).getPerId(), list.get(0).getPerId());
		Assert.assertEquals(listDTO.get(0).getPerDescription(), list.get(0).getPerDescription());
		Assert.assertEquals(listDTO.get(0).getPerDirector(), list.get(0).getPerDirector());
		Assert.assertEquals(listDTO.get(0).getPerDuration(), list.get(0).getPerDuration());
		Assert.assertEquals(listDTO.get(0).getPerGenre(), list.get(0).getPerGenre());
		Assert.assertEquals((int) listDTO.get(0).getPerPrice(), (int) list.get(0).getPerPrice());
		Assert.assertEquals(listDTO.get(0).getPerRank(), list.get(0).getPerRank());
		Assert.assertEquals(listDTO.get(0).getPerType(), list.get(0).getPerType());

		Assert.assertEquals(listDTO.get(1).getPerId(), list.get(1).getPerId());
		Assert.assertEquals(listDTO.get(1).getPerDescription(), list.get(1).getPerDescription());
		Assert.assertEquals(listDTO.get(1).getPerDirector(), list.get(1).getPerDirector());
		Assert.assertEquals(listDTO.get(1).getPerDuration(), list.get(1).getPerDuration());
		Assert.assertEquals(listDTO.get(1).getPerGenre(), list.get(1).getPerGenre());
		Assert.assertEquals((int) listDTO.get(1).getPerPrice(), (int) list.get(1).getPerPrice());
		Assert.assertEquals(listDTO.get(1).getPerRank(), list.get(1).getPerRank());
		Assert.assertEquals(listDTO.get(1).getPerType(), list.get(1).getPerType());

		mock.assertIsSatisfied();
	}
}
