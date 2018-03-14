package com.management.repositories;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.management.entities.Performance;
import com.management.interfaces.PerformanceRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
@Repository
public class PerformanceRepository implements PerformanceRepositoryInterface {
	private Session session;

	public PerformanceRepository(Session session) {
		this.session = session;

	}

	public void Add(Performance entity) {
		session.beginTransaction();
		session.save(entity);
	}

	public Performance Read(int id) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (Performance) session.load(Performance.class, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Performance> ReadAll() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (ArrayList<Performance>) session.createCriteria(
				Performance.class).list();
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
		session.delete(session.load(Performance.class, id));
	}
}
