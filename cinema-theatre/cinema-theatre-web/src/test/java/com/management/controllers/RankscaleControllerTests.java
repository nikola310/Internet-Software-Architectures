package com.management.controllers;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.dto.RankscaleDTO;
import com.management.entities.Rankscale;
import com.management.entities.User;
import com.management.fake.RankscaleRepositoryFake;
import com.management.managers.RankscaleManager;
import com.management.repositories.RankscaleRepository;

/**
 * @author Nikola Stojanovic
 *
 */
public class RankscaleControllerTests {

	private RankscaleRepository repository;

	@Test
	public void AddingNewRankscale_ReturnsOK() {
		// Arrange
		repository = new RankscaleRepositoryFake();

		RankscaleDTO dto = new RankscaleDTO();
		dto.setScaleActive(false);
		dto.setScaleBronze(500);
		dto.setScaleGold(1000);
		dto.setScaleSilver(750);
		dto.setUserId(0);
		RankscaleManager manager = new RankscaleManager(repository);
		RankscaleController controller = new RankscaleController(manager);

		// Act and assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.addRankscale(dto),
				new ResponseEntity<RankscaleDTO>(dto, HttpStatus.OK));
	}

	@Test
	public void DeletingReservation_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		repository = mock.mock(RankscaleRepository.class);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(repository).delete(1);
			}
		});

		// Act and assert
		RankscaleManager manager = new RankscaleManager(repository);
		RankscaleController controller = new RankscaleController(manager);

		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.deleteRankscale(1),
				new ResponseEntity<RankscaleDTO>(HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadRankscale_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		repository = mock.mock(RankscaleRepository.class);

		final Rankscale rs = new Rankscale();
		rs.setScaleActive(true);
		rs.setScaleBronze(500);
		rs.setScaleGold(2500);
		rs.setScaleSilver(1500);
		User u = new User();
		u.setUserId(0);
		rs.setUser(u);

		mock.checking(new Expectations() {
			{
				oneOf(repository).findOne(1);
				will(returnValue(rs));
			}
		});

		RankscaleManager manager = new RankscaleManager(repository);
		RankscaleController controller = new RankscaleController(manager);

		// Act
		ResponseEntity<RankscaleDTO> response = controller.getRankscale(1);
		RankscaleDTO dto = response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<RankscaleDTO>(dto,
				HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllRankscales_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		repository = mock.mock(RankscaleRepository.class);

		final ArrayList<Rankscale> list = new ArrayList<Rankscale>();

		Rankscale r1 = new Rankscale();
		r1.setScaleActive(true);
		r1.setScaleBronze(500);
		r1.setScaleGold(2500);
		r1.setScaleSilver(1500);
		User u = new User();
		u.setUserId(0);
		r1.setUser(u);

		Rankscale r2 = new Rankscale();
		r2.setScaleActive(false);
		r2.setScaleBronze(1500);
		r2.setScaleGold(5000);
		r2.setScaleSilver(10000);
		User u1 = new User();
		u1.setUserId(1);
		r1.setUser(u1);

		list.add(r1);
		list.add(r2);

		mock.checking(new Expectations() {
			{
				oneOf(repository).findAll();
				will(returnValue(list));
			}
		});

		RankscaleManager manager = new RankscaleManager(repository);
		RankscaleController controller = new RankscaleController(manager);

		// Act
		ResponseEntity<List<RankscaleDTO>> response = controller
				.getRankscales();
		ArrayList<RankscaleDTO> listDTO = (ArrayList<RankscaleDTO>) response
				.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<List<RankscaleDTO>>(
				listDTO, HttpStatus.OK));

		mock.assertIsSatisfied();
	}
}
