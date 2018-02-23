package com.management.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.management.interfaces.BaseReposytoryInterface;

public class BaseRepository implements BaseReposytoryInterface<Object>{

	protected Session session;

	@SuppressWarnings("deprecation")
	public BaseRepository() {
		Configuration cf=new Configuration();
		cf.configure();
		SessionFactory sf = cf.buildSessionFactory();
		session =sf.openSession();
	}
	
	public void Add(Object entity) {
		session.save(entity);
		
	}

	public void Read(int id) {
		session.get(Object.class, id);
	}

	public void Update(Object entity) {
		session.update(entity);
		
	}

	public void Delete(Object entity) {
		session.delete(entity);		
	}

}
