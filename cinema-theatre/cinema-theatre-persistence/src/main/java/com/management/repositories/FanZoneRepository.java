package com.management.repositories;

import org.hibernate.classic.Session;

import com.management.entities.FanZone;
import com.management.interfaces.FanZoneRepositoryInterface;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class FanZoneRepository implements FanZoneRepositoryInterface {

	private Session session;

	public FanZoneRepository(Session session) {
		this.session = session;
	}

	public void Add(FanZone entity) {
		session.beginTransaction();
		session.save(entity);
	}

	public FanZone Read(int id) {
		session.beginTransaction();
		return (FanZone) session.load(FanZone.class, id);
	}

	public void Update() {
		session.beginTransaction();
	}

	public void Delete(int id) {
		session.beginTransaction();
		session.delete(session.get(FanZone.class, id));
	}

}
