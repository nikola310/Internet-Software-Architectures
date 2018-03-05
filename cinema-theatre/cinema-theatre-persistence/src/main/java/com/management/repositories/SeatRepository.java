package com.management.repositories;

import java.util.ArrayList;

import org.hibernate.Session;

import com.management.entities.Seat;
import com.management.interfaces.SeatRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class SeatRepository implements SeatRepositoryInterface{
	private Session session;

	public SeatRepository(Session session) {
		this.session = session;

	}
	
	public void Add(Seat entity) {
		session.beginTransaction();
		session.save(entity);
	}

	public Seat Read(int id) {
		session.beginTransaction();
		return (Seat) session.load(Seat.class, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Seat> ReadAll() {
		session.beginTransaction();
		return (ArrayList<Seat>) session.createCriteria(Seat.class).list();
	}

	public void Update() {
		session.beginTransaction();
	}

	public void Delete(int id) {
		session.beginTransaction();
		session.delete(session.load(Seat.class, id));
	}
}
