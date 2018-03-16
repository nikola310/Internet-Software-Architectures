package com.management.fake;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.management.entities.FanZone;
import com.management.repositories.FanZoneRepository;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class FanZoneRepositoryFake implements FanZoneRepository {

	private FanZone entity;
	
	public void deleteAllInBatch() {

	}

	public void deleteInBatch(Iterable<FanZone> arg0) {

	}

	public List<FanZone> findAll() {
		return null;
	}

	public List<FanZone> findAll(Sort arg0) {
		return null;
	}

	public List<FanZone> findAll(Iterable<Integer> arg0) {
		return null;
	}

	public <S extends FanZone> List<S> findAll(Example<S> arg0) {
		return null;
	}

	public <S extends FanZone> List<S> findAll(Example<S> arg0, Sort arg1) {
		return null;
	}

	public void flush() {

	}

	public FanZone getOne(Integer arg0) {
		return null;
	}

	public <S extends FanZone> List<S> save(Iterable<S> arg0) {
		return null;
	}

	public <S extends FanZone> S saveAndFlush(S arg0) {
		return null;
	}

	public Page<FanZone> findAll(Pageable arg0) {
		return null;
	}

	public long count() {
		return 0;
	}

	public void delete(Integer arg0) {

	}

	public void delete(FanZone arg0) {

	}

	public void delete(Iterable<? extends FanZone> arg0) {

	}

	public void deleteAll() {

	}

	public boolean exists(Integer arg0) {
		return false;
	}

	public FanZone findOne(Integer arg0) {
		return entity;
	}

	public <S extends FanZone> S save(S arg0) {
		entity = arg0;
		return arg0;
	}

	public <S extends FanZone> long count(Example<S> arg0) {
		return 0;
	}

	public <S extends FanZone> boolean exists(Example<S> arg0) {
		return false;
	}

	public <S extends FanZone> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		return null;
	}

	public <S extends FanZone> S findOne(Example<S> arg0) {
		return null;
	}

}
