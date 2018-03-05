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
import com.management.interfaces.ActorPerformancesRepositoryInterface;
import com.management.interfaces.ActorRepositoryInterface;
import com.management.interfaces.CinemaTheatreRepositoryInterface;
import com.management.interfaces.EventRepositoryInterface;
import com.management.interfaces.FanZoneRepositoryInterface;
import com.management.interfaces.FriendsListRepositoryInterface;
import com.management.interfaces.HallRepositoryInterface;
import com.management.interfaces.HistoryRepositoryInterface;
import com.management.interfaces.PerformanceRepositoryInterface;
import com.management.interfaces.PropsRepositoryInterface;
import com.management.interfaces.SeatRepositoryInterface;
import com.management.interfaces.UnitOfWorkInterface;
import com.management.interfaces.UserRepositoryInterface;
import com.management.repositories.ActorPerformancesRepository;
import com.management.repositories.ActorRepository;
import com.management.repositories.CinemaTheatreRepository;
import com.management.repositories.EventRepository;
import com.management.repositories.FanZoneRepository;
import com.management.repositories.FriendsListRepository;
import com.management.repositories.HallRepository;
import com.management.repositories.HistoryRepository;
import com.management.repositories.PerformanceRepository;
import com.management.repositories.PropsRepository;
import com.management.repositories.SeatRepository;
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
	private CinemaTheatreRepositoryInterface cinemaTheatreRepository;
	private ActorPerformancesRepositoryInterface actorPerformancesRepository;
	private ActorRepositoryInterface actorRepository;
	private EventRepositoryInterface eventRepository;
	private HallRepositoryInterface hallRepository;
	private HistoryRepositoryInterface historyRepository;
	private PerformanceRepositoryInterface performanceRepository;
	private SeatRepositoryInterface seatRepository;
	private FanZoneRepositoryInterface fanZoneRepository;
	private PropsRepositoryInterface propsRepository;

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
		cinemaTheatreRepository = new CinemaTheatreRepository(session);
		actorPerformancesRepository = new ActorPerformancesRepository(session);
		actorRepository = new ActorRepository(session);
		eventRepository = new EventRepository(session);
		hallRepository = new HallRepository(session);
		historyRepository = new HistoryRepository(session);
		performanceRepository = new PerformanceRepository(session);
		seatRepository = new SeatRepository(session);
		fanZoneRepository = new FanZoneRepository(session);
		propsRepository = new PropsRepository(session);
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
		return cinemaTheatreRepository;
	}

	public ActorPerformancesRepositoryInterface getActorPerformancesRepository() {
		return actorPerformancesRepository;
	}

	public ActorRepositoryInterface getActorRepository() {
		return actorRepository;
	}

	public EventRepositoryInterface getEventRepository() {
		return eventRepository;
	}

	public HallRepositoryInterface getHallRepository() {
		return hallRepository;
	}

	public HistoryRepositoryInterface getHistoryRepository() {
		return historyRepository;
	}

	public PerformanceRepositoryInterface getPerformanceRepository() {
		return performanceRepository;
	}

	public SeatRepositoryInterface getSeatRepository() {
		return seatRepository;
	}

	public FanZoneRepositoryInterface getFanZoneRepository() {
		return fanZoneRepository;
	}

	public PropsRepositoryInterface getPropsRepository() {
		return propsRepository;
	}
}
