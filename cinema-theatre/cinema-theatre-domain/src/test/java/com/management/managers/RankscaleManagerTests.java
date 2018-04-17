package com.management.managers;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.RankscaleDTO;
import com.management.entities.Rankscale;
import com.management.entities.User;
import com.management.fake.RankscaleRepositoryFake;
import com.management.repositories.RankscaleRepository;

/**
 * @author Nikola Stojanovic
 *
 */
@Service
@Transactional
public class RankscaleManagerTests {

	private RankscaleRepository repository;

	@Test
	public void AddingNewRankscale_ReturnsBoolean() {
		// Arrange
		repository = new RankscaleRepositoryFake();

		RankscaleDTO dto = new RankscaleDTO();
		dto.setScaleActive(false);
		dto.setScaleBronze(300);
		dto.setScaleSilver(500);
		dto.setScaleGold(1000);
		RankscaleManager manager = new RankscaleManager(repository);

		// Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));

		Rankscale entity = repository.findOne(0);
		Assert.assertEquals(dto.getScaleId(), entity.getScaleId());
		Assert.assertEquals(dto.getScaleBronze(), entity.getScaleBronze());
		Assert.assertEquals(dto.getScaleGold(), entity.getScaleGold());
		Assert.assertEquals(dto.getScaleSilver(), entity.getScaleSilver());
		Assert.assertEquals(dto.getUserId(), entity.getUser().getUserId());
	}

	@Test
	public void DeletingRankscale_ReturnsBoolean() {
		// Arrange
		Mockery mock = new Mockery();
		repository = mock.mock(RankscaleRepository.class);

		// Expectations
		mock.checking(new Expectations() {
			{
				oneOf(repository).delete(1);
			}
		});

		// Act and assert
		RankscaleManager manager = new RankscaleManager(repository);
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Delete(1));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadRankscale_ReturnsRankscale() {
		// Arrange
		Mockery mock = new Mockery();
		repository = mock.mock(RankscaleRepository.class);

		final Rankscale entity = new Rankscale();
		entity.setScaleBronze(300);
		entity.setScaleGold(5000);
		entity.setScaleSilver(2000);
		entity.setScaleActive(true);
		User u = new User();
		u.setUserId(0);
		entity.setUser(u);
		
		mock.checking(new Expectations() {
			{
				oneOf(repository).findOne(1);
				will(returnValue(entity));
			}
		});

		RankscaleManager manager = new RankscaleManager(repository);

		// Act
		RankscaleDTO dto = manager.Read(1);

		// Assert
		Assert.assertNotNull(dto);

		Assert.assertEquals(dto.getScaleId(), entity.getScaleId());
		Assert.assertEquals(dto.getScaleBronze(), entity.getScaleBronze());
		Assert.assertEquals(dto.getScaleGold(), entity.getScaleGold());
		Assert.assertEquals(dto.getScaleSilver(), entity.getScaleSilver());
		Assert.assertEquals(dto.getUserId(), entity.getUser().getUserId());
		Assert.assertEquals(dto.isScaleActive(), entity.isScaleActive());
		
		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllRankscale_ReturnsAllRankscale() {
		// Arrange
		Mockery mock = new Mockery();
		repository = mock.mock(RankscaleRepository.class);

		final ArrayList<Rankscale> list = new ArrayList<Rankscale>();

		Rankscale r1 = new Rankscale();
		r1.setScaleActive(true);
		r1.setScaleBronze(1000);
		r1.setScaleSilver(3000);
		r1.setScaleGold(5000);

		Rankscale r2 = new Rankscale();
		r2.setScaleActive(false);
		r2.setScaleBronze(500);
		r2.setScaleSilver(1500);
		r2.setScaleGold(3500);

		list.add(r1);
		list.add(r2);

		mock.checking(new Expectations() {
			{
				oneOf(repository).findAll();
				will(returnValue(list));
			}
		});

		RankscaleManager manager = new RankscaleManager(repository);

		// Act
		ArrayList<RankscaleDTO> dtoList = manager.ReadAll();

		// Assert
		Assert.assertNotNull(dtoList);

		Assert.assertEquals(dtoList.get(0).getScaleId(), list.get(0)
				.getScaleId());
		Assert.assertEquals(dtoList.get(0).getScaleBronze(), list.get(0)
				.getScaleBronze());
		Assert.assertEquals(dtoList.get(0).getScaleGold(), list.get(0)
				.getScaleGold());
		Assert.assertEquals(dtoList.get(0).getScaleSilver(), list.get(0)
				.getScaleSilver());
		Assert.assertEquals(dtoList.get(0).isScaleActive(), list.get(0)
				.isScaleActive());
		
		Assert.assertEquals(dtoList.get(1).getScaleId(), list.get(1)
				.getScaleId());
		Assert.assertEquals(dtoList.get(1).getScaleBronze(), list.get(1)
				.getScaleBronze());
		Assert.assertEquals(dtoList.get(1).getScaleGold(), list.get(1)
				.getScaleGold());
		Assert.assertEquals(dtoList.get(1).getScaleSilver(), list.get(1)
				.getScaleSilver());
		Assert.assertEquals(dtoList.get(1).isScaleActive(), list.get(1)
				.isScaleActive());
		
		mock.assertIsSatisfied();
	}
}
