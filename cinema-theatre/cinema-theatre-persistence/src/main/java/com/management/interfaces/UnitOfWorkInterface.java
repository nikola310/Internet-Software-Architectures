package com.management.interfaces;

public interface UnitOfWorkInterface {
	public void commitChanges();
	
	public UserRepositoryInterface getUserRepository();
	public FriendsListRepositoryInterface getFriendsListRepository();
}
