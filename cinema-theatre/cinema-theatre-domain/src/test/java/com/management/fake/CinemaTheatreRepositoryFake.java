package com.management.fake;

import java.util.ArrayList;

import com.management.entities.CinemaTheatre;
import com.management.interfaces.CinemaTheatreRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class CinemaTheatreRepositoryFake implements CinemaTheatreRepositoryInterface{

	private ArrayList<CinemaTheatre> list;
	
	public CinemaTheatreRepositoryFake() {
		list = new ArrayList<CinemaTheatre>();
	}
	
	public void Add(CinemaTheatre entity) {
		entity.setCtId(list.size());
		list.add(entity);
	}

	public CinemaTheatre Read(int id) {
		return list.get(id);
	}

	public void Update() {
		
	}

	public void Delete(int id) {
		list.remove(id);
	}

	/* (non-Javadoc)
	 * @see com.management.interfaces.RepositoryInterface#ReadAll()
	 */
	public ArrayList<CinemaTheatre> ReadAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
