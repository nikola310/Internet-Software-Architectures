package com.management.fake;

import java.util.ArrayList;

import com.management.entities.User;
import com.management.interfaces.UserRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class UserRepositoryFake implements UserRepositoryInterface{

	private ArrayList<User> list;
	
	public UserRepositoryFake() {
		list = new ArrayList<User>(); 
	}
	public void Add(User entity) {
		entity.setUserId(list.size());
		list.add(entity);
		
	}

	public User Read(int id) {
		return list.get(id);
	}

	public void Update(User entity) {
		list.set(list.indexOf(entity), entity);
	}

	public void Delete(int id) {
		list.remove(id);
	}

}
