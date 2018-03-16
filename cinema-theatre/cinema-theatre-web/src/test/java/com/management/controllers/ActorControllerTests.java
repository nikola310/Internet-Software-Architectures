package com.management.controllers;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.dto.ActorDTO;
import com.management.managers.ActorManager;
import com.management.repositories.ActorRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorControllerTests {
	
	private ActorRepository actorRepository;

	@Test
	public void test() {
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
}
