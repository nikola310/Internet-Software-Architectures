package com.management.repositories;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.management.entities.FanZone;
import com.management.interfaces.FanZoneRepositoryInterface;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
@Repository
public class FanZoneRepository implements FanZoneRepositoryInterface {

	private Session session;

	public FanZoneRepository(Session session) {
		this.session = session;
	}

	public void Add(FanZone entity) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		session.save(entity);
	}

	public FanZone Read(int id) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (FanZone) session.load(FanZone.class, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<FanZone> ReadAll() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (ArrayList<FanZone>) session.createCriteria(FanZone.class)
				.list();
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
		session.delete(session.get(FanZone.class, id));
	}
}
