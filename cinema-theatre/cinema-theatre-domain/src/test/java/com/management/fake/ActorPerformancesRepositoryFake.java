package com.management.fake;

import java.util.ArrayList;

import com.management.entities.Actorperformances;
import com.management.interfaces.ActorPerformancesRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorPerformancesRepositoryFake implements ActorPerformancesRepositoryInterface{

	private ArrayList<Actorperformances> list;
	
	public ActorPerformancesRepositoryFake() {
		list = new ArrayList<Actorperformances>(); 
	}
	public void Add(Actorperformances entity) {
		entity.setApId(list.size());
		list.add(entity);
		
	}

	public Actorperformances Read(int id) {
		return list.get(id);
	}

	public void Update() {
	}

	public void Delete(int id) {
		list.remove(id);
	}

	public ArrayList<Actorperformances> ReadAll() {
		return list;
	}
}
