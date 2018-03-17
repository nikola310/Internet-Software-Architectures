package com.management.controllers;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.dto.ActorPerformancesDTO;
import com.management.entities.Actorperformances;
import com.management.fake.ActorPerformancesRepositoryFake;
import com.management.managers.ActorPerformancesManager;
import com.management.repositories.ActorPerformancesRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorPerformancesControllerTests {

	private ActorPerformancesRepository actorReprformancesRepository;

	@Test
	public void AddingNewActorPerformances_ReturnsOK() {
		// Arrange
		actorReprformancesRepository = new ActorPerformancesRepositoryFake();

		ActorPerformancesDTO dto = new ActorPerformancesDTO();
		ActorPerformancesManager manager = new ActorPerformancesManager(actorReprformancesRepository);
		ActorPerformancesController controller = new ActorPerformancesController(manager);

		// Act and assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.addActorPerformances(dto),
				new ResponseEntity<ActorPerformancesDTO>(dto, HttpStatus.OK));
	}

	@Test
	public void DeletingActorPerformances_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		actorReprformancesRepository = mock.mock(ActorPerformancesRepository.class);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(actorReprformancesRepository).delete(1);
			}
		});

		// Act and assert
		ActorPerformancesManager manager = new ActorPerformancesManager(actorReprformancesRepository);
		ActorPerformancesController controller = new ActorPerformancesController(manager);

		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.deleteActorPerformances(1),
				new ResponseEntity<ActorPerformancesDTO>(HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadActorPerformances_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		actorReprformancesRepository = mock.mock(ActorPerformancesRepository.class);

		final Actorperformances ap = new Actorperformances();

		mock.checking(new Expectations() {
			{
				oneOf(actorReprformancesRepository).findOne(1);
				will(returnValue(ap));
			}
		});

		ActorPerformancesManager manager = new ActorPerformancesManager(actorReprformancesRepository);
		ActorPerformancesController controller = new ActorPerformancesController(manager);

		// Act
		ResponseEntity<ActorPerformancesDTO> response = controller.getActorPerformances(1);
		ActorPerformancesDTO dto = response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<ActorPerformancesDTO>(dto, HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllActorPerformances_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		actorReprformancesRepository = mock.mock(ActorPerformancesRepository.class);

		final ArrayList<Actorperformances> list = new ArrayList<Actorperformances>();

		Actorperformances ap1 = new Actorperformances();
		Actorperformances ap2 = new Actorperformances();

		list.add(ap1);
		list.add(ap2);

		mock.checking(new Expectations() {
			{
				oneOf(actorReprformancesRepository).findAll();
				will(returnValue(list));
			}
		});

		ActorPerformancesManager manager = new ActorPerformancesManager(actorReprformancesRepository);
		ActorPerformancesController controller = new ActorPerformancesController(manager);

		// Act
		ResponseEntity<List<ActorPerformancesDTO>> response = controller.getActorPerformances();
		ArrayList<ActorPerformancesDTO> listDTO = (ArrayList<ActorPerformancesDTO>) response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<List<ActorPerformancesDTO>>(listDTO, HttpStatus.OK));

		mock.assertIsSatisfied();
	}
}
