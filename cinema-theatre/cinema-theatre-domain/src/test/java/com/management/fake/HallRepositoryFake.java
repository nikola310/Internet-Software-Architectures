package com.management.fake;

import java.util.ArrayList;

import com.management.entities.Hall;
import com.management.interfaces.HallRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class HallRepositoryFake implements HallRepositoryInterface{

	private ArrayList<Hall> list;
	
	public HallRepositoryFake() {
		list = new ArrayList<Hall>(); 
	}
	public void Add(Hall entity) {
		entity.setHallId(list.size());
		list.add(entity);
		
	}

	public Hall Read(int id) {
		return list.get(id);
	}

	public void Update() {
	}

	public void Delete(int id) {
		list.remove(id);
	}

	public ArrayList<Hall> ReadAll() {
		return list;
	}
}
