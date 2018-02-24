package com.management.repositories;

import org.hibernate.Session;

import com.management.entities.Friendslist;
import com.management.interfaces.FriendsListRepositoryInterface;

/**
 * 
 * @author Zivko Stanisic
 *
 */
public class FriendsListRepository implements FriendsListRepositoryInterface {
	private Session session;

	public FriendsListRepository(Session session) {
		this.session = session;
	}

	public void Add(Friendslist entity) {
		session.beginTransaction();
		session.save(entity);
	}

	public Friendslist Read(int id) {
		session.beginTransaction();
		return (Friendslist) session.load(Friendslist.class, id);
	}

	public void Update() {
		session.beginTransaction();
	}

	public void Delete(int id) {
		session.beginTransaction();
		session.delete(session.get(Friendslist.class, id));
	}
}
