package com.management.repositories;

import java.util.ArrayList;

import org.hibernate.Session;

import com.management.entities.Event;
import com.management.interfaces.EventRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class EventRepository implements EventRepositoryInterface {
	private Session session;

	public EventRepository(Session session) {
		this.session = session;

	}

	public void Add(Event entity) {
		session.beginTransaction();
		session.save(entity);

	}

	public Event Read(int id) {
		session.beginTransaction();
		return (Event) session.load(Event.class, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Event> ReadAll() {
		session.beginTransaction();
		return (ArrayList<Event>) session.createCriteria(Event.class).list();
	}

	public void Update() {
		session.beginTransaction();
	}

	public void Delete(int id) {
		session.delete(session.load(Event.class, id));
	}
}
