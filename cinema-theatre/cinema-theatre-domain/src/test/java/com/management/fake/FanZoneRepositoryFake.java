package com.management.fake;

import java.util.ArrayList;

import com.management.entities.FanZone;
import com.management.interfaces.FanZoneRepositoryInterface;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class FanZoneRepositoryFake implements FanZoneRepositoryInterface {

	private ArrayList<FanZone> list;

	public FanZoneRepositoryFake() {
		list = new ArrayList<FanZone>();
	}

	public void Add(FanZone entity) {
		entity.setFanZoneId(list.size());
		list.add(entity);
	}

	public FanZone Read(int id) {
		return list.get(id);
	}

	public ArrayList<FanZone> ReadAll() {
		return list;
	}

	public void Update() {

	}

	public void Delete(int id) {
		list.remove(id);
	}
}
