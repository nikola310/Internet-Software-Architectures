package com.management.repositories;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.management.entities.Seat;
import com.management.interfaces.SeatRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
@Repository
public class SeatRepository implements SeatRepositoryInterface {
	private Session session;

	public SeatRepository(Session session) {
		this.session = session;

	}

	public void Add(Seat entity) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		session.save(entity);
	}

	public Seat Read(int id) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (Seat) session.load(Seat.class, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Seat> ReadAll() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (ArrayList<Seat>) session.createCriteria(Seat.class).list();
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
		session.delete(session.load(Seat.class, id));
	}
}
