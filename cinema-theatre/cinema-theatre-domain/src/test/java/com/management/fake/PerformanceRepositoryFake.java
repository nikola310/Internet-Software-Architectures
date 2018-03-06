package com.management.fake;

import java.util.ArrayList;

import com.management.entities.Performance;
import com.management.interfaces.PerformanceRepositoryInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class PerformanceRepositoryFake implements PerformanceRepositoryInterface{

	private ArrayList<Performance> list;
	
	public PerformanceRepositoryFake() {
		list = new ArrayList<Performance>(); 
	}
	public void Add(Performance entity) {
		entity.setPerId(list.size());
		list.add(entity);
		
	}

	public Performance Read(int id) {
		return list.get(id);
	}

	public void Update() {
	}

	public void Delete(int id) {
		list.remove(id);
	}

	public ArrayList<Performance> ReadAll() {
		return list;
	}
}
