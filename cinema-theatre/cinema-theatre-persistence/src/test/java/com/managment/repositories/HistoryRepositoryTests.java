package com.managment.repositories;

import java.util.ArrayList;
import java.util.Date;

import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.management.context.UnitOfWork;
import com.management.entities.CinemaTheatre;
import com.management.entities.Event;
import com.management.entities.Hall;
import com.management.entities.History;
import com.management.entities.Performance;
import com.management.entities.User;

public class HistoryRepositoryTests {

	private UnitOfWork uow;

	public HistoryRepositoryTests() {
		uow = new UnitOfWork();
	}

	@Test
	public void CRUD_HistoryTests() {
		Mockery mock = new Mockery();

		// Arrange
		int key = 0;
		History history = new History();
		history.setHistoryId(key);
		history.setHistoryPrice(300);
		Event event = new Event();
		event.setEventId(key);
		history.setEvent(event);

		//===============================
		CinemaTheatre cinemaTheatre = new CinemaTheatre();
		cinemaTheatre.setCtAdress("Bulevar oslobodjenja 11");
		cinemaTheatre.setCtDescription("Ovo je opis.");
		cinemaTheatre.setCtName("Bioskop");
		cinemaTheatre.setCtPhone(123456789);
		cinemaTheatre.setCtStateid("381");
		Hall h = new Hall();
		h.setHallName("A1");
		h.setCinemaTheatre(cinemaTheatre);
		Performance p = new Performance();
		p.setPerId(1);
		event.setPerformance(p);
		event.setHall(h);
		event.setEventDate(new Date());
		User user = new User();
		user.setUserActive(true);
		user.setUserName("Zivko");
		user.setUserSurname("Stanisic");
		user.setUserAdmin('N');
		user.setUserCity("Novi Sad");
		user.setUserCreationDate(new Date());
		user.setUserEmail("zivko@gmail.com");
		user.setUserPassword("123");
		user.setUserRank(0);
		user.setUserPhone(123456);
		user.setUserStateid("381");
		//================================
		history.setUser(user);
		
		// Act
		uow.getCinemaTheatreRepository().Add(cinemaTheatre);
		uow.getHallRepository().Add(h);
		uow.getEventRepository().Add(event);
		uow.getUserRepository().Add(user);
		uow.getHistoryRepository().Add(history);
		uow.commitChanges();

		// Arrange and act
		ArrayList<History> lista = uow.getHistoryRepository().ReadAll();

		for (History tmp : lista) {
			if (tmp.getHistoryPrice() == 300 && tmp.getUser().getUserId() == user.getUserId()
					&& tmp.getEvent().getEventId() == event.getEventId()) {
				key = tmp.getHistoryId();
				break;
			}
		}

		history = uow.getHistoryRepository().Read(key);

		// Assert
		Assert.assertNotNull(history);

		int t = 300;
		Assert.assertEquals(t, history.getHistoryPrice());
		Assert.assertEquals(event, history.getEvent());
		
		// Arrange
		history = uow.getHistoryRepository().Read(key);
		uow.commitChanges();

		Assert.assertNotNull(history);

		history.setHistoryPrice(400);
		
		// Act
		uow.getHistoryRepository().Update();
		uow.commitChanges();

		uow.getHistoryRepository().Delete(key);
		uow.getEventRepository().Delete(event.getEventId());
		uow.getHallRepository().Delete(h.getHallId());
		uow.getCinemaTheatreRepository().Delete(cinemaTheatre.getCtId());
		uow.getUserRepository().Delete(user.getUserId());
		uow.commitChanges();

		// Assert
		mock.assertIsSatisfied();
	}
}
