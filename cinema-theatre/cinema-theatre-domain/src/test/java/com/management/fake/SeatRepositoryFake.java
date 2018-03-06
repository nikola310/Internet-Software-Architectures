package com.management.fake;

import java.util.ArrayList;

import com.management.entities.Seat;
import com.management.interfaces.SeatRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class SeatRepositoryFake implements SeatRepositoryInterface{

	private ArrayList<Seat> list;
	
	public SeatRepositoryFake() {
		list = new ArrayList<Seat>(); 
	}
	public void Add(Seat entity) {
		entity.setSeatId(list.size());
		list.add(entity);
		
	}

	public Seat Read(int id) {
		return list.get(id);
	}

	public void Update() {
	}

	public void Delete(int id) {
		list.remove(id);
	}

	public ArrayList<Seat> ReadAll() {
		return list;
	}
}
