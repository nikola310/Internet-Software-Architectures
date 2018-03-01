package com.management.fake;

import com.management.interfaces.CinemaTheatreRepositoryInterface;
import com.management.interfaces.FriendsListRepositoryInterface;
import com.management.interfaces.UnitOfWorkInterface;
import com.management.interfaces.UserRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class UnitOfWorkFake implements UnitOfWorkInterface{

	private UserRepositoryInterface userRepository;
	private FriendsListRepositoryInterface friendsListRepository;
	private CinemaTheatreRepositoryInterface cinemaTheatre;
	
	public UnitOfWorkFake() {
		userRepository = new UserRepositoryFake();
		friendsListRepository = new FriendsListRepositoryFake();
		cinemaTheatre = new CinemaTheatreRepositoryFake();
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
		return cinemaTheatre;
	}

}
