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
public class ActorPerformancesRepository implements ActorPerformancesRepositoryInterface{
	private Session session;

	public ActorPerformancesRepository(Session session) {
		this.session = session;

	}
	
	public void Add(Actorperformances entity) {
		session.beginTransaction();
		session.save(entity);
		
	}

	public Actorperformances Read(int id) {
		session.beginTransaction();
		return (Actorperformances) session.load(Actorperformances.class, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Actorperformances> ReadAll() {
		session.beginTransaction();
		return (ArrayList<Actorperformances>) session.createCriteria(Actorperformances.class).list();
	}


	public void Update() {
		session.beginTransaction();
		
	}


	public void Delete(int id) {
		session.beginTransaction();
		session.delete(session.load(Actorperformances.class, id));
		
	}
}
