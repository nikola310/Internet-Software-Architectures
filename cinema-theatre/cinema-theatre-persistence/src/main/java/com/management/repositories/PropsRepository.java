package com.management.repositories;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.management.entities.Props;
import com.management.interfaces.PropsRepositoryInterface;

@Repository
public class PropsRepository implements PropsRepositoryInterface {

	private Session session;

	public PropsRepository(Session session) {
		this.session = session;
	}

	public void Add(Props entity) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		session.save(entity);
	}

	public Props Read(int id) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (Props) session.load(Props.class, id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Props> ReadAll() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return (ArrayList<Props>) session.createCriteria(Props.class).list();
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
		session.delete(session.get(Props.class, id));
	}
}
