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
}
