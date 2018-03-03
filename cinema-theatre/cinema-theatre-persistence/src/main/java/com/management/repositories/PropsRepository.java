package com.management.repositories;

import org.hibernate.classic.Session;

import com.management.entities.Props;
import com.management.interfaces.PropsRepositoryInterface;

public class PropsRepository implements PropsRepositoryInterface {

	private Session session;

	public PropsRepository(Session session) {
		this.session = session;
	}

	public void Add(Props entity) {
		session.beginTransaction();
		session.save(entity);
	}

	public Props Read(int id) {
		session.beginTransaction();
		return (Props) session.load(Props.class, id);
	}

	public void Update() {
		session.beginTransaction();
	}

	public void Delete(int id) {
		session.beginTransaction();
		session.delete(session.get(Props.class, id));
	}

}
