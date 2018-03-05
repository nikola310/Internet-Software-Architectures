package com.management.fake;

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

/**
 * @author Zivko Stanisic
 *
 */
public class UnitOfWorkFake implements UnitOfWorkInterface{

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
	
	public UnitOfWorkFake() {
		userRepository = new UserRepositoryFake();
		friendsListRepository = new FriendsListRepositoryFake();
		cinemaTheatreRepository = new CinemaTheatreRepositoryFake();
	}
	
	public void commitChanges() {
		
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
		return null;
	}

	public PropsRepositoryInterface getPropsRepository() {
		return null;
	}

}
