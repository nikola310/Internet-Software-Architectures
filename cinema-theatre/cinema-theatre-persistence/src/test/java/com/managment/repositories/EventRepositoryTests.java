package com.managment.repositories;

import java.util.ArrayList;
import java.util.Date;

import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.context.UnitOfWork;
import com.management.entities.Event;
import com.management.entities.Hall;
import com.management.entities.Performance;

/**
 * @author Zivko Stanisic
 *
 */
public class EventRepositoryTests {
	private UnitOfWork uow;

	public EventRepositoryTests() {
		uow = new UnitOfWork();
	}

	@Test
	public void CRUD_EventTest() {
		Mockery mock = new Mockery();

		// Arrange
		int key = 0;
		Event event = new Event();
		event.setEventDate(new Date());

		Hall hall = new Hall();
		hall.setHallName("A1");

		Performance per = new Performance();
		per.setPerCreationDate(new Date());
		per.setPerDescription("Neki opis.");
		per.setPerDirector("Nikola Stojanovic");
		per.setPerDuration(11);
		per.setPerGenre("Komedija");
		per.setPerPoster(new byte[] { 121 });
		per.setPerPrice(100);
		per.setPerRank(0);
		per.setPerType('M');

		event.setHall(hall);
		event.setPerformance(per);

		// Act
		uow.getHallRepository().Add(hall);
		uow.getPerformanceRepository().Add(per);
		uow.getEventRepository().Add(event);
		uow.commitChanges();

		// Arrange and act
		ArrayList<Event> list = uow.getEventRepository().ReadAll();

		for (Event tmp : list) {
			if (tmp.getHall().getHallName().equals("A1")
					&& tmp.getPerformance().getPerDirector().equals("Nikola Stojanovic")) {
				key = tmp.getEventId();
				break;
			}
		}

		event = uow.getEventRepository().Read(key);

		// Assert
		Assert.assertNotNull(event);

		Assert.assertEquals(event.getHall().getHallName(), "A1");
		Assert.assertEquals(event.getPerformance().getPerDescription(), "Neki opis.");
		Assert.assertEquals(event.getPerformance().getPerDirector(), "Nikola Stojanovic");
		Assert.assertEquals(event.getPerformance().getPerDuration(), 11);
		Assert.assertEquals(event.getPerformance().getPerGenre(), "Komedija");
		Assert.assertEquals(event.getPerformance().getPerPoster(), new byte[] { 121 });
		Assert.assertEquals((int) event.getPerformance().getPerPrice(), 100);
		Assert.assertEquals(event.getPerformance().getPerRank(), 0);
		Assert.assertEquals(event.getPerformance().getPerType(), 'M');

		// Arrange
		event = uow.getEventRepository().Read(key);
		uow.commitChanges();

		Assert.assertNotNull(event);

		event.getPerformance().setPerRank(11);

		// Act
		uow.getEventRepository().Update();
		uow.commitChanges();

		uow.getPerformanceRepository().Delete(event.getPerformance().getPerId());
		uow.getHallRepository().Delete(event.getHall().getHallId());
		uow.getEventRepository().Delete(key);
		uow.commitChanges();

		// Assert
		mock.assertIsSatisfied();
	}
}
