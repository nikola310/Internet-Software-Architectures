package com.management.repositories;

import java.util.ArrayList;

import org.hibernate.Session;

import com.management.entities.Hall;
import com.management.interfaces.HallRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class HallRepository implements HallRepositoryInterface{
	private Session session;

	public HallRepository(Session session) {
		this.session = session;

	}
	
	public void Add(Hall entity) {
		session.beginTransaction();
		session.save(entity);
		
	}

	public Hall Read(int id) {
		session.beginTransaction();
		return (Hall) session.load(Hall.class, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Hall> ReadAll() {
		session.beginTransaction();
		return (ArrayList<Hall>) session.createCriteria(Hall.class).list();
	}

	public void Update() {
		session.beginTransaction();	
	}

	public void Delete(int id) {
		session.delete(session.load(Hall.class, id));
	}
}
