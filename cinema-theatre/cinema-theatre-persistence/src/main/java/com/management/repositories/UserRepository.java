package com.management.repositories;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.management.entities.User;
import com.management.interfaces.UserRepositoryInterface;

/**
 * 
 * @author Zivko Stanisic
 *
 */
@Repository
public class UserRepository implements UserRepositoryInterface {
	private Session session;

	public UserRepository(Session session) {
		this.session = session;
	}

	public void Add(User entity) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		session.save(entity);
	}

	public User Read(int id) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (User) session.load(User.class, id);

	}

	@SuppressWarnings("unchecked")
	public ArrayList<User> ReadAll() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (ArrayList<User>) session.createCriteria(User.class).list();
	}

	public void Update() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
	}

	public void Delete(int id) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		session.delete(session.get(User.class, id));
	}

}
