package com.managment.repositories;

import java.util.ArrayList;
import java.util.Date;

import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.context.UnitOfWork;
import com.management.entities.Performance;

/**
 * @author Zivko Stanisic
 *
 */
public class PerformanceRepositoryTests {
	private UnitOfWork uow;

	public PerformanceRepositoryTests() {
		uow = new UnitOfWork();
	}

	@Test
	public void CRUD_PerformanceTest() {
		Mockery mock = new Mockery();

		// Arrange
		int key = 0;
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

		// Act
		uow.getPerformanceRepository().Add(per);
		uow.commitChanges();

		// Arrange and act
		ArrayList<Performance> list = uow.getPerformanceRepository().ReadAll();

		for (Performance tmp : list) {
			if (tmp.getPerDirector().equals("Nikola Stojanovic")) {
				key = tmp.getPerId();
				break;
			}
		}

		per = uow.getPerformanceRepository().Read(key);

		// Assert
		Assert.assertNotNull(per);

		Assert.assertEquals(per.getPerDescription(), "Neki opis.");
		Assert.assertEquals(per.getPerDirector(), "Nikola Stojanovic");
		Assert.assertEquals(per.getPerDuration(), 11);
		Assert.assertEquals(per.getPerGenre(), "Komedija");
		Assert.assertArrayEquals(per.getPerPoster(), new byte[] {121});
		Assert.assertEquals((int)per.getPerPrice(), 100);
		Assert.assertEquals(per.getPerRank(), 0);
		Assert.assertEquals(per.getPerType(), 'M');
		
		// Arrange
		per = uow.getPerformanceRepository().Read(key);
		uow.commitChanges();

		Assert.assertNotNull(per);

		per.setPerRank(11);

		// Act
		uow.getPerformanceRepository().Update();
		uow.commitChanges();

		uow.getPerformanceRepository().Delete(key);
		uow.commitChanges();

		// Assert
		mock.assertIsSatisfied();
	}
}
