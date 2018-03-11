package com.management.fake;

import java.util.ArrayList;

import com.management.entities.History;
import com.management.interfaces.HistoryRepositoryInterface;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class HistoryRepositoryFake implements HistoryRepositoryInterface {

	private ArrayList<History> list;

	public HistoryRepositoryFake() {
		list = new ArrayList<History>();
	}

	public void Add(History entity) {
		entity.setHistoryId(list.size());
		list.add(entity);
	}

	public History Read(int id) {
		return list.get(id);
	}

	public ArrayList<History> ReadAll() {
		return list;
	}

	public void Update() {
	}

	public void Delete(int id) {
		list.remove(id);
	}

}
