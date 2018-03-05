package com.management.interfaces;

/**
 * 
 * @author Zivko Stanisic
 *
 */
public interface UnitOfWorkInterface {
	public void commitChanges();
	
	public UserRepositoryInterface getUserRepository();
	public FriendsListRepositoryInterface getFriendsListRepository();
	public CinemaTheatreRepositoryInterface getCinemaTheatreRepository();
	public ActorPerformancesRepositoryInterface getActorPerformancesRepository();
	public ActorRepositoryInterface getActorRepository();
	public EventRepositoryInterface getEventRepository();
	public HallRepositoryInterface getHallRepository();
	public HistoryRepositoryInterface getHistoryRepository();
	public PerformanceRepositoryInterface getPerformanceRepository();
	public SeatRepositoryInterface getSeatRepository();
	public FanZoneRepositoryInterface getFanZoneRepository();
	public PropsRepositoryInterface getPropsRepository();
}
