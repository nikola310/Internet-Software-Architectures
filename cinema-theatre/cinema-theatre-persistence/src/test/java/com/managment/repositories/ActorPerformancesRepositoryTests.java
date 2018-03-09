package com.managment.repositories;

import java.util.ArrayList;
import java.util.Date;

import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.context.UnitOfWork;
import com.management.entities.Actor;
import com.management.entities.Actorperformances;
import com.management.entities.Performance;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorPerformancesRepositoryTests {
	private UnitOfWork uow;

	public ActorPerformancesRepositoryTests() {
		uow = new UnitOfWork();
	}

	@Test
	public void CRUD_ActorPerformancesTest() {
		Mockery mock = new Mockery();

		// Arrange
		int key = 0;
		Actorperformances ap = new Actorperformances();
		
		Actor actor = new Actor();
		actor.setAcName("Zivko");
		actor.setAcSurname("Stanisic");
		
		Performance per = new Performance();
		per.setPerCreationDate(new Date());
		per.setPerDescription("Neki opis.");
		per.setPerDirector("Nikola Stojanovic");
		per.setPerDuration(11);
		per.setPerGenre("Komedija");
		per.setPerPoster(new byte[] {121});
		per.setPerPrice(100);
		per.setPerRank(0);
		per.setPerType('M');
		
		ap.setActor(actor);
		ap.setPerformance(per);

		// Act
		uow.getActorRepository().Add(actor);
		uow.getPerformanceRepository().Add(per);
		uow.getActorPerformancesRepository().Add(ap);
		uow.commitChanges();

		// Arrange and act
		ArrayList<Actorperformances> list = uow.getActorPerformancesRepository().ReadAll();

		for (Actorperformances tmp : list) {
			if (tmp.getActor().getAcName().equals("Zivko") && tmp.getActor().getAcSurname().equals("Stanisic")) {
				key = tmp.getApId();
				break;
			}
		}

		System.err.println(key);
		ap = uow.getActorPerformancesRepository().Read(key);

		// Assert
		Assert.assertNotNull(ap);

		Assert.assertEquals(ap.getActor().getAcName(), "Zivko");
		Assert.assertEquals(ap.getActor().getAcSurname(), "Stanisic");
		Assert.assertEquals(ap.getPerformance().getPerDescription(), "Neki opis.");
		Assert.assertEquals(ap.getPerformance().getPerDirector(), "Nikola Stojanovic");
		Assert.assertEquals(ap.getPerformance().getPerDuration(), 11);
		Assert.assertEquals(ap.getPerformance().getPerGenre(), "Komedija");
		Assert.assertArrayEquals(ap.getPerformance().getPerPoster(), new byte[] {121});
		Assert.assertEquals((int)ap.getPerformance().getPerPrice(), 100);
		Assert.assertEquals(ap.getPerformance().getPerRank(), 11);
		Assert.assertEquals(ap.getPerformance().getPerType(), 'M');
		
		// Arrange
		ap = uow.getActorPerformancesRepository().Read(key);
		uow.commitChanges();

		Assert.assertNotNull(ap);

		ap.getPerformance().setPerRank(11);

		// Act
		uow.getActorPerformancesRepository().Update();
		uow.commitChanges();

		uow.getPerformanceRepository().Delete(ap.getPerformance().getPerId());
		uow.getActorRepository().Delete(ap.getActor().getAcId());
		uow.getActorPerformancesRepository().Delete(key);
		uow.commitChanges();

		// Assert
		mock.assertIsSatisfied();
	}
}
