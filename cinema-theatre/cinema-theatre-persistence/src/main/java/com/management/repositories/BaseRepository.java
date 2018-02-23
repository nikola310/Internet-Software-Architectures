package com.management.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.management.entities.CinemaTheatre;
import com.management.entities.Event;
import com.management.entities.FanZone;
import com.management.entities.Friendslist;
import com.management.entities.Hall;
import com.management.entities.History;
import com.management.entities.Performance;
import com.management.entities.Props;
import com.management.entities.Seat;
import com.management.entities.User;
import com.management.interfaces.BaseReposytoryInterface;
/**
 * 
 * @author Zivko Stanisic
 *
 */
public class BaseRepository implements BaseReposytoryInterface<Object> {

	protected static Session session;
	protected static SessionFactory sessionFactory;

	public BaseRepository() {
		Configuration cf = new Configuration();
		cf.addClass(User.class);
		cf.addClass(CinemaTheatre.class);
		cf.addClass(Event.class);
		cf.addClass(FanZone.class);
		cf.addClass(Friendslist.class);
		cf.addClass(Hall.class);
		cf.addClass(History.class);
		cf.addClass(Performance.class);
		cf.addClass(Props.class);
		cf.addClass(Seat.class);
		
		cf.configure();
		try {
			StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(cf.getProperties());
            sessionFactory = cf.buildSessionFactory(ssrb.build());
            session = sessionFactory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Add(Object entity) {
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();

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
