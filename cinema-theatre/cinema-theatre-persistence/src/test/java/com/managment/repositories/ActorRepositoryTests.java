package com.managment.repositories;

import java.util.ArrayList;

import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.context.UnitOfWork;
import com.management.entities.Actor;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorRepositoryTests {
	private UnitOfWork uow;

	public ActorRepositoryTests() {
		uow = new UnitOfWork();
	}

	@Test
	public void CRUD_ActorTest() {
		Mockery mock = new Mockery();

		// Arrange
		int key = 0;
		Actor actor = new Actor();
		actor.setAcName("Zivko");
		actor.setAcSurname("Stanisic");

		// Act
		uow.getActorRepository().Add(actor);
		uow.commitChanges();

		// Arrange and act
		ArrayList<Actor> list = uow.getActorRepository().ReadAll();

		for (Actor tmp : list) {
			if (tmp.getAcName().equals("Zivko") && tmp.getAcSurname().equals("Stanisic")) {
				key = tmp.getAcId();
				break;
			}
		}

		actor = uow.getActorRepository().Read(key);

		// Assert
		Assert.assertNotNull(actor);

		Assert.assertEquals(actor.getAcName(), "Zivko");
		Assert.assertEquals(actor.getAcSurname(), "Stanisic");
		
		// Arrange
		actor = uow.getActorRepository().Read(key);
		uow.commitChanges();

		Assert.assertNotNull(actor);

		actor.setAcName("Ziki");

		// Act
		uow.getActorRepository().Update();
		uow.commitChanges();

		uow.getActorRepository().Delete(key);
		uow.commitChanges();

		// Assert
		mock.assertIsSatisfied();
	}
}
