package com.management.fake;

import java.util.ArrayList;

import com.management.entities.Props;
import com.management.interfaces.PropsRepositoryInterface;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class PropsRepositoryFake implements PropsRepositoryInterface {

	private ArrayList<Props> list;

	public PropsRepositoryFake() {
		list = new ArrayList<Props>();
	}

	public void Add(Props entity) {
		entity.setPropsId(list.size());
		list.add(entity);
	}

	public Props Read(int id) {
		return list.get(id);
	}

	public ArrayList<Props> ReadAll() {
		return list;
	}

	public void Update() {
	}

	public void Delete(int id) {
		list.remove(id);
	}

}
