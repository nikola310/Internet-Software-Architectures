package com.management.managers;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.dto.ReservationDTO;
import com.management.entities.Props;
import com.management.entities.Reservation;
import com.management.entities.User;
import com.management.fake.ReservationRepositoryFake;
import com.management.repositories.ReservationRepository;

/**
 * @author Nikola Stojanovic
 *
 */
public class ReservationManagerTests {

	private ReservationRepository repository;

	@Test
	public void AddingNewReservation_ReturnsBoolean() {
		// Arrange
		repository = new ReservationRepositoryFake();

		ReservationDTO dto = new ReservationDTO();
		dto.setPropsId(0);
		dto.setReservationId(0);
		dto.setUserId(0);

		ReservationManager manager = new ReservationManager(repository);

		// Act and assert
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Create(dto));

		Reservation entity = repository.findOne(0);
		Assert.assertEquals(dto.getPropsId(), entity.getProps().getPropsId());
		Assert.assertEquals(dto.getReservationId(), entity.getReservationId());
		Assert.assertEquals(dto.getUserId(), entity.getUser().getUserId());
	}

	@Test
	public void DeletingReservation_ReturnsBoolean() {
		// Arrange
		Mockery mock = new Mockery();
		repository = mock.mock(ReservationRepository.class);

		// Expectations
		mock.checking(new Expectations() {
			{
				oneOf(repository).delete(1);
			}
		});

		// Act and assert
		ReservationManager manager = new ReservationManager(repository);
		Assert.assertNotNull(manager);
		Assert.assertTrue(manager.Delete(1));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadReservation_ReturnsReservation() {
		// Arrange
		Mockery mock = new Mockery();
		repository = mock.mock(ReservationRepository.class);

		final Reservation entity = new Reservation();
		Props props = new Props();
		props.setPropsId(0);
		entity.setProps(props);
		entity.setReservationId(0);
		User u = new User();
		u.setUserId(0);
		entity.setUser(u);

		mock.checking(new Expectations() {
			{
				oneOf(repository).findOne(1);
				will(returnValue(entity));
			}
		});

		ReservationManager manager = new ReservationManager(repository);

		// Act
		ReservationDTO dto = manager.Read(1);

		// Assert
		Assert.assertNotNull(dto);

		Assert.assertEquals(dto.getReservationId(), entity.getReservationId());
		Assert.assertEquals(dto.getPropsId(), entity.getProps().getPropsId());
		Assert.assertEquals(dto.getUserId(), entity.getUser().getUserId());

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllReservations_ReturnsAllReservations() {
		// Arrange
		Mockery mock = new Mockery();
		repository = mock.mock(ReservationRepository.class);

		final ArrayList<Reservation> list = new ArrayList<Reservation>();

		Reservation r1 = new Reservation();
		Props props = new Props();
		props.setPropsId(0);
		r1.setProps(props);
		r1.setReservationId(0);
		User u = new User();
		u.setUserId(0);
		r1.setUser(u);

		Reservation r2 = new Reservation();
		Props props1 = new Props();
		props1.setPropsId(1);
		r2.setProps(props1);
		r2.setReservationId(1);
		User u1 = new User();
		u1.setUserId(1);
		r2.setUser(u);

		list.add(r1);
		list.add(r2);

		mock.checking(new Expectations() {
			{
				oneOf(repository).findAll();
				will(returnValue(list));
			}
		});

		ReservationManager manager = new ReservationManager(repository);

		// Act
		ArrayList<ReservationDTO> dtoList = manager.ReadAll();

		// Assert
		Assert.assertNotNull(dtoList);

		Assert.assertEquals(dtoList.get(0).getReservationId(), list.get(0)
				.getReservationId());
		Assert.assertEquals(dtoList.get(0).getPropsId(), list.get(0)
				.getProps().getPropsId());
		Assert.assertEquals(dtoList.get(0).getUserId(), list.get(0)
				.getUser().getUserId());

		Assert.assertEquals(dtoList.get(1).getReservationId(), list.get(1)
				.getReservationId());
		Assert.assertEquals(dtoList.get(1).getPropsId(), list.get(1)
				.getProps().getPropsId());
		Assert.assertEquals(dtoList.get(1).getUserId(), list.get(1)
				.getUser().getUserId());

		mock.assertIsSatisfied();
	}

}
