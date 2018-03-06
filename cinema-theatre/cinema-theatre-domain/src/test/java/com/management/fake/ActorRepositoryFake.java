package com.management.fake;

import java.util.ArrayList;

import com.management.entities.Actor;
import com.management.interfaces.ActorRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorRepositoryFake implements ActorRepositoryInterface{

	private ArrayList<Actor> list;
	
	public ActorRepositoryFake() {
		list = new ArrayList<Actor>(); 
	}
	public void Add(Actor entity) {
		entity.setAcId(list.size());
		list.add(entity);
		
	}

	public Actor Read(int id) {
		return list.get(id);
	}

	public void Update() {
	}

	public void Delete(int id) {
		list.remove(id);
	}

	public ArrayList<Actor> ReadAll() {
		return list;
	}
}
