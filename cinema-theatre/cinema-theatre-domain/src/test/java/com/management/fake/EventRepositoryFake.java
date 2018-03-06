package com.management.fake;

import java.util.ArrayList;

import com.management.entities.Event;
import com.management.interfaces.EventRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class EventRepositoryFake implements EventRepositoryInterface{

	private ArrayList<Event> list;
	
	public EventRepositoryFake() {
		list = new ArrayList<Event>(); 
	}
	public void Add(Event entity) {
		entity.setEventId(list.size());
		list.add(entity);
		
	}

	public Event Read(int id) {
		return list.get(id);
	}

	public void Update() {
	}

	public void Delete(int id) {
		list.remove(id);
	}

	public ArrayList<Event> ReadAll() {
		return list;
	}
}
