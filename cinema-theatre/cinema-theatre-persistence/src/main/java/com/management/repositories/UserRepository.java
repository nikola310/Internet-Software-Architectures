package com.management.repositories;

import org.hibernate.Session;

import com.management.entities.User;
import com.management.interfaces.UserRepositoryInterface;

/**
 * 
 * @author Zivko Stanisic
 *
 */
public class UserRepository implements UserRepositoryInterface {
	private Session session;

	public UserRepository(Session session) {
		this.session = session;

	}

	public void Add(User entity) {
		session.beginTransaction();
		session.save(entity);

	}

	public User Read(int id) {
		session.beginTransaction();
		return (User) session.get(User.class, id);

	}

	public void Update(User entity) {
		session.beginTransaction();
		session.update(entity);

	}

	public void Delete(int id) {
		session.beginTransaction();
		session.delete(session.get(User.class, id));
	}

}
