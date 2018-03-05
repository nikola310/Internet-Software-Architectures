package com.management.fake;

import java.util.ArrayList;

import com.management.entities.Friendslist;
import com.management.interfaces.FriendsListRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class FriendsListRepositoryFake implements FriendsListRepositoryInterface{

	private ArrayList<Friendslist> list;
	
	public FriendsListRepositoryFake() {
		list = new ArrayList<Friendslist>();
	}
	
	public void Add(Friendslist entity) {
		list.add(entity);
		
	}

	public Friendslist Read(int id) {
		return list.get(id);
	}

	public void Update() {
	}

	public void Delete(int id) {
		list.remove(id);
	}

	/* (non-Javadoc)
	 * @see com.management.interfaces.RepositoryInterface#ReadAll()
	 */
	public ArrayList<Friendslist> ReadAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
