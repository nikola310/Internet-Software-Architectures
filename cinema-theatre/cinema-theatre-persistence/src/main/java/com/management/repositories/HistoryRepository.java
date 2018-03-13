package com.management.repositories;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.management.entities.History;
import com.management.interfaces.HistoryRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
@Repository
public class HistoryRepository implements HistoryRepositoryInterface{
	private Session session;

	public HistoryRepository(Session session) {
		this.session = session;

	}
	
	public void Add(History entity) {
		session.beginTransaction();
		session.save(entity);
	}

	public History Read(int id) {
		session.beginTransaction();
		return (History) session.load(History.class, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<History> ReadAll() {
		session.beginTransaction();
		return (ArrayList<History>) session.createCriteria(History.class).list();
	}

	public void Update() {
		session.beginTransaction();	
	}

	public void Delete(int id) {
		session.delete(session.load(History.class, id));	
	}
}
