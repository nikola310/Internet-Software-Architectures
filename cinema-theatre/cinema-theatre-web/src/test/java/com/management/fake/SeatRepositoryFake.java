package com.management.fake;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.management.entities.Seat;
import com.management.repositories.SeatRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class SeatRepositoryFake implements SeatRepository{
	
	private Seat entity;
	

	public void deleteAllInBatch() {
		
	}

	public void deleteInBatch(Iterable<Seat> arg0) {
		
	}

	public List<Seat> findAll() {
		return null;
	}

	public List<Seat> findAll(Sort arg0) {
		return null;
	}

	public List<Seat> findAll(Iterable<Integer> arg0) {
		return null;
	}

	public <S extends Seat> List<S> findAll(Example<S> arg0) {
		return null;
	}

	public <S extends Seat> List<S> findAll(Example<S> arg0, Sort arg1) {
		return null;
	}

	public void flush() {
		
	}

	public Seat getOne(Integer arg0) {
		return null;
	}

	public <S extends Seat> List<S> save(Iterable<S> arg0) {
		return null;
	}

	public <S extends Seat> S saveAndFlush(S arg0) {
		return null;
	}

	public Page<Seat> findAll(Pageable arg0) {
		return null;
	}

	public long count() {
		return 0;
	}

	public void delete(Integer arg0) {
		
	}

	public void delete(Seat arg0) {
		
	}

	public void delete(Iterable<? extends Seat> arg0) {
		
	}

	public void deleteAll() {
		
	}

	public boolean exists(Integer arg0) {
		return false;
	}

	public Seat findOne(Integer arg0) {
		return entity;
	}

	public <S extends Seat> S save(S arg0) {
		entity = arg0;
		return arg0;
	}

	public <S extends Seat> long count(Example<S> arg0) {
		return 0;
	}

	public <S extends Seat> boolean exists(Example<S> arg0) {
		return false;
	}

	public <S extends Seat> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		return null;
	}

	public <S extends Seat> S findOne(Example<S> arg0) {
		return null;
	}
}
