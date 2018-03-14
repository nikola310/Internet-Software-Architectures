package com.management.repositories;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.management.entities.Event;
import com.management.interfaces.EventRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
@Repository
public class EventRepository implements EventRepositoryInterface {
	private Session session;

	public EventRepository(Session session) {
		this.session = session;

	}

	public void Add(Event entity) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		session.save(entity);

	}

	public Event Read(int id) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (Event) session.load(Event.class, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Event> ReadAll() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (ArrayList<Event>) session.createCriteria(Event.class).list();
	}

	public void Update() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
	}

	public void Delete(int id) {
		if (!session.getTransaction().isActive()) {
			session.delete(session.load(Event.class, id));
		}
	}
}
