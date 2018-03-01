package com.management.repositories;

import org.hibernate.Session;

import com.management.entities.CinemaTheatre;
import com.management.interfaces.CinemaTheatreRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class CinemaTheatreRepository implements CinemaTheatreRepositoryInterface {

	private Session session;

	public CinemaTheatreRepository(Session session) {
		this.session = session;
	}

	public void Add(CinemaTheatre entity) {
		session.beginTransaction();
		session.save(entity);
	}

	public CinemaTheatre Read(int id) {
		session.beginTransaction();
		return (CinemaTheatre) session.load(CinemaTheatre.class, id);
	}

	public void Update() {
		session.beginTransaction();
	}

	public void Delete(int id) {
		session.beginTransaction();
		session.delete(session.get(CinemaTheatre.class, id));
	}

}