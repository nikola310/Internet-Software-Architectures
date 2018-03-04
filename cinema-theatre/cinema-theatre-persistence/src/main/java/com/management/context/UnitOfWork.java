package com.management.context;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.management.entities.Actor;
import com.management.entities.Actorperformances;
import com.management.entities.CinemaTheatre;
import com.management.entities.Event;
import com.management.entities.FanZone;
import com.management.entities.Friendslist;
import com.management.entities.Hall;
import com.management.entities.History;
import com.management.entities.Performance;
import com.management.entities.Props;
import com.management.entities.Seat;
import com.management.entities.User;
import com.management.interfaces.CinemaTheatreRepositoryInterface;
import com.management.interfaces.FriendsListRepositoryInterface;
import com.management.interfaces.UnitOfWorkInterface;
import com.management.interfaces.UserRepositoryInterface;
import com.management.repositories.CinemaTheatreRepository;
import com.management.repositories.FriendsListRepository;
import com.management.repositories.UserRepository;

/**
 * 
 * @author Zivko Stanisic
 *
 */
public class UnitOfWork implements UnitOfWorkInterface {

	private Session session;
	private UserRepositoryInterface userRepository;
	private FriendsListRepositoryInterface friendsListRepository;
	private CinemaTheatreRepositoryInterface cinemaTheatre;

	public UnitOfWork() {
		Configuration cf = new Configuration();
		cf.addClass(User.class);
		cf.addClass(CinemaTheatre.class);
		cf.addClass(Event.class);
		cf.addClass(FanZone.class);
		cf.addClass(Friendslist.class);
		cf.addClass(Hall.class);
		cf.addClass(History.class);
		cf.addClass(Performance.class);
		cf.addClass(Props.class);
		cf.addClass(Seat.class);
		cf.addClass(Actor.class);
		cf.addClass(Actorperformances.class);

		cf.configure();

		try {
			SessionFactory sessionFactory = cf.buildSessionFactory();
			session = sessionFactory.openSession();

		} catch (Exception e) {
			e.printStackTrace();
		}

		userRepository = new UserRepository(session);
		friendsListRepository = new FriendsListRepository(session);
		cinemaTheatre = new CinemaTheatreRepository(session);
	}

	public void commitChanges() {
		session.getTransaction().commit();

	}

	public UserRepositoryInterface getUserRepository() {
		return userRepository;
	}

	public FriendsListRepositoryInterface getFriendsListRepository() {
		return friendsListRepository;
	}

	public CinemaTheatreRepositoryInterface getCinemaTheatreRepository() {
		return cinemaTheatre;
	}

}
