package com.management.controllers;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.dto.ActorDTO;
import com.management.entities.Actor;
import com.management.fake.ActorRepositoryFake;
import com.management.managers.ActorManager;
import com.management.repositories.ActorRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorControllerTests {

	private ActorRepository actorRepository;

	@Test
	public void AddingNewActor_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		actorRepository = new ActorRepositoryFake();

		ActorDTO dto = new ActorDTO();
		dto.setAcName("Zivko");
		dto.setAcSurname("Stanisic");
		ActorManager manager = new ActorManager(actorRepository);
		ActorController controller = new ActorController(manager);

		// Act and assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.addActor(dto), new ResponseEntity<ActorDTO>(dto, HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void DeletingActor_ReturnsOK() {
		Mockery mock = new Mockery();
		actorRepository = mock.mock(ActorRepository.class);

		// expectations
		mock.checking(new Expectations() {
			{
				oneOf(actorRepository).delete(1);
			}
		});

		ActorManager manager = new ActorManager(actorRepository);
		// Act and assert
		ActorController controller = new ActorController(manager);
		Assert.assertNotNull(controller);
		Assert.assertEquals(controller.deleteActor(1), new ResponseEntity<ActorDTO>(HttpStatus.OK));

		mock.assertIsSatisfied();

	}

	@Test
	public void ReadActor_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		actorRepository = mock.mock(ActorRepository.class);

		final Actor actor = new Actor();
		actor.setAcName("Zivko");
		actor.setAcSurname("Stanisic");

		mock.checking(new Expectations() {
			{
				oneOf(actorRepository).findOne(1);
				will(returnValue(actor));
			}
		});

		ActorManager manager = new ActorManager(actorRepository);
		ActorController controller = new ActorController(manager);

		// Act
		ResponseEntity<ActorDTO> response = controller.getActor(1);
		ActorDTO dto = response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<ActorDTO>(dto, HttpStatus.OK));

		mock.assertIsSatisfied();
	}

	@Test
	public void ReadAllActors_ReturnsOK() {
		// Arrange
		Mockery mock = new Mockery();
		actorRepository = mock.mock(ActorRepository.class);

		final ArrayList<Actor> list = new ArrayList<Actor>();

		Actor actor1 = new Actor();
		actor1.setAcName("Zivko");
		actor1.setAcSurname("Stanisic");

		Actor actor2 = new Actor();
		actor2.setAcName("Nikola");
		actor2.setAcSurname("Stojanovic");

		list.add(actor1);
		list.add(actor2);

		mock.checking(new Expectations() {
			{
				oneOf(actorRepository).findAll();
				will(returnValue(list));
			}
		});

		ActorManager manager = new ActorManager(actorRepository);
		ActorController controller = new ActorController(manager);

		// Act
		ResponseEntity<List<ActorDTO>> response = controller.getActors();
		ArrayList<ActorDTO> listDTO = (ArrayList<ActorDTO>) response.getBody();

		// Assert
		Assert.assertNotNull(controller);
		Assert.assertEquals(response, new ResponseEntity<List<ActorDTO>>(listDTO, HttpStatus.OK));

		mock.assertIsSatisfied();
	}
}
