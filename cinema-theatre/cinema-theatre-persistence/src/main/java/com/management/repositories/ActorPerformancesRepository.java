package com.management.repositories;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.management.entities.Actorperformances;
import com.management.interfaces.ActorPerformancesRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
@Repository
public class ActorPerformancesRepository implements
		ActorPerformancesRepositoryInterface {
	private Session session;

	public ActorPerformancesRepository(Session session) {
		this.session = session;

	}

	public void Add(Actorperformances entity) {
		session.beginTransaction();
		session.save(entity);

	}

	public Actorperformances Read(int id) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (Actorperformances) session.load(Actorperformances.class, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Actorperformances> ReadAll() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (ArrayList<Actorperformances>) session.getCriteriaBuilder()
				.createQuery(Actorperformances.class);
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
		session.delete(session.load(Actorperformances.class, id));

	}
}
