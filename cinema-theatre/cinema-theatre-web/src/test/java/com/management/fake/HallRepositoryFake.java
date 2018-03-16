package com.management.fake;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.management.entities.Hall;
import com.management.repositories.HallRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class HallRepositoryFake implements HallRepository{

	private Hall entity;
	

	public void deleteAllInBatch() {
		
	}

	public void deleteInBatch(Iterable<Hall> arg0) {
		
	}

	public List<Hall> findAll() {
		return null;
	}

	public List<Hall> findAll(Sort arg0) {
		return null;
	}

	public List<Hall> findAll(Iterable<Integer> arg0) {
		return null;
	}

	public <S extends Hall> List<S> findAll(Example<S> arg0) {
		return null;
	}

	public <S extends Hall> List<S> findAll(Example<S> arg0, Sort arg1) {
		return null;
	}

	public void flush() {
		
	}

	public Hall getOne(Integer arg0) {
		return null;
	}

	public <S extends Hall> List<S> save(Iterable<S> arg0) {
		return null;
	}

	public <S extends Hall> S saveAndFlush(S arg0) {
		return null;
	}

	public Page<Hall> findAll(Pageable arg0) {
		return null;
	}

	public long count() {
		return 0;
	}

	public void delete(Integer arg0) {
		
	}

	public void delete(Hall arg0) {
		
	}

	public void delete(Iterable<? extends Hall> arg0) {
		
	}

	public void deleteAll() {
		
	}

	public boolean exists(Integer arg0) {
		return false;
	}

	public Hall findOne(Integer arg0) {
		return entity;
	}

	public <S extends Hall> S save(S arg0) {
		entity = arg0;
		return arg0;
	}

	public <S extends Hall> long count(Example<S> arg0) {
		return 0;
	}

	public <S extends Hall> boolean exists(Example<S> arg0) {
		return false;
	}

	public <S extends Hall> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		return null;
	}

	public <S extends Hall> S findOne(Example<S> arg0) {
		return null;
	}
}
