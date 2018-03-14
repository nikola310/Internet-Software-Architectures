package com.management.repositories;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.management.entities.Actor;
import com.management.interfaces.ActorRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
@Repository
public class ActorRepository implements ActorRepositoryInterface {
	private Session session;

	public ActorRepository(Session session) {
		this.session = session;

	}

	public void Add(Actor entity) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		session.save(entity);
	}

	public Actor Read(int id) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (Actor) session.load(Actor.class, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Actor> ReadAll() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (ArrayList<Actor>) session.createCriteria(Actor.class).list();
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
		session.delete(session.load(Actor.class, id));

	}
}
