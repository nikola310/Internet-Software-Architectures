package com.management.fake;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.management.entities.Event;
import com.management.repositories.EventRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class EventRepositoryFake implements EventRepository{
	
	private Event entity;

	public void deleteAllInBatch() {
		
	}

	public void deleteInBatch(Iterable<Event> arg0) {
		
	}

	public List<Event> findAll() {
		return null;
	}

	public List<Event> findAll(Sort arg0) {
		return null;
	}

	public List<Event> findAll(Iterable<Integer> arg0) {
		return null;
	}

	public <S extends Event> List<S> findAll(Example<S> arg0) {
		return null;
	}

	public <S extends Event> List<S> findAll(Example<S> arg0, Sort arg1) {
		return null;
	}

	public void flush() {
		
	}

	public Event getOne(Integer arg0) {
		return null;
	}

	public <S extends Event> List<S> save(Iterable<S> arg0) {
		return null;
	}

	public <S extends Event> S saveAndFlush(S arg0) {
		return null;
	}

	public Page<Event> findAll(Pageable arg0) {
		return null;
	}

	public long count() {
		return 0;
	}

	public void delete(Integer arg0) {
		
	}

	public void delete(Event arg0) {
		
	}

	public void delete(Iterable<? extends Event> arg0) {
		
	}

	public void deleteAll() {
		
	}

	public boolean exists(Integer arg0) {
		return false;
	}

	public Event findOne(Integer arg0) {
		return entity;
	}

	public <S extends Event> S save(S arg0) {
		entity = arg0;
		return arg0;
	}

	public <S extends Event> long count(Example<S> arg0) {
		return 0;
	}

	public <S extends Event> boolean exists(Example<S> arg0) {
		return false;
	}

	public <S extends Event> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		return null;
	}

	public <S extends Event> S findOne(Example<S> arg0) {
		return null;
	}
}
