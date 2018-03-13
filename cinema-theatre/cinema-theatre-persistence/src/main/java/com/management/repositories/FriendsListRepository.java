package com.management.repositories;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.management.entities.Friendslist;
import com.management.interfaces.FriendsListRepositoryInterface;

/**
 * 
 * @author Zivko Stanisic
 *
 */
@Repository
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

	@SuppressWarnings("unchecked")
	public ArrayList<Friendslist> ReadAll() {
		session.beginTransaction();
		return (ArrayList<Friendslist>) session.createCriteria(Friendslist.class).list();
	}

	public void Update() {
		session.beginTransaction();
	}

	public void Delete(int id) {
		session.beginTransaction();
		session.delete(session.get(Friendslist.class, id));
	}
}
