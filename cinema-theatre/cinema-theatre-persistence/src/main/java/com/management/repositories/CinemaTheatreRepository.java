package com.management.repositories;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.management.entities.CinemaTheatre;
import com.management.interfaces.CinemaTheatreRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
@Repository
public class CinemaTheatreRepository implements
		CinemaTheatreRepositoryInterface {

	private Session session;

	public CinemaTheatreRepository(Session session) {
		this.session = session;
	}

	public void Add(CinemaTheatre entity) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		session.save(entity);
	}

	public CinemaTheatre Read(int id) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (CinemaTheatre) session.load(CinemaTheatre.class, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<CinemaTheatre> ReadAll() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (ArrayList<CinemaTheatre>) session.createCriteria(
				CinemaTheatre.class).list();
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
		session.delete(session.get(CinemaTheatre.class, id));
	}
}
