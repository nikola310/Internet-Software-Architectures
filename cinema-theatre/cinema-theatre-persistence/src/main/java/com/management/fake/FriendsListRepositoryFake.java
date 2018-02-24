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

	public void Update(Friendslist entity) {
		list.set(list.indexOf(entity), entity);
	}

	public void Delete(int id) {
		list.remove(id);
	}

}
