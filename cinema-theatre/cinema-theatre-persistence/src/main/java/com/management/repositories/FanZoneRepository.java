package com.management.repositories;

import java.util.ArrayList;

import org.hibernate.Session;

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

	@SuppressWarnings("unchecked")
	public ArrayList<FanZone> ReadAll() {
		session.beginTransaction();
		return (ArrayList<FanZone>) session.createCriteria(FanZone.class).list();
	}

	public void Update() {
		session.beginTransaction();
	}

	public void Delete(int id) {
		session.beginTransaction();
		session.delete(session.get(FanZone.class, id));
	}
}
