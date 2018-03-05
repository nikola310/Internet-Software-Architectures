package com.management.repositories;

import java.util.ArrayList;

import org.hibernate.Session;

import com.management.entities.Actor;
import com.management.interfaces.ActorRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorRepository implements ActorRepositoryInterface{
	private Session session;

	public ActorRepository(Session session) {
		this.session = session;

	}
	
	public void Add(Actor entity) {
		session.beginTransaction();
		session.save(entity);
	}


	public Actor Read(int id) {
		session.beginTransaction();
		return (Actor) session.load(Actor.class, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Actor> ReadAll() {
		session.beginTransaction();
		return (ArrayList<Actor>) session.createCriteria(Actor.class).list();
	}

	public void Update() {
		session.beginTransaction();
		
	}

	public void Delete(int id) {
		session.beginTransaction();
		session.delete(session.load(Actor.class, id));
		
	}
}
