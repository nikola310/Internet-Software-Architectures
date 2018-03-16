package com.management.fake;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.management.entities.Actorperformances;
import com.management.repositories.ActorPerformancesRepository;

/**
 * @author Zivko Stanisic
 *
 */
public class ActorPerformancesRepositoryFake implements ActorPerformancesRepository{

	private Actorperformances entity;
	
	public void deleteAllInBatch() {
		
	}

	public void deleteInBatch(Iterable<Actorperformances> arg0) {
		
	}

	public List<Actorperformances> findAll() {
		return null;
	}

	public List<Actorperformances> findAll(Sort arg0) {
		return null;
	}

	public List<Actorperformances> findAll(Iterable<Integer> arg0) {
		return null;
	}

	public <S extends Actorperformances> List<S> findAll(Example<S> arg0) {
		return null;
	}

	public <S extends Actorperformances> List<S> findAll(Example<S> arg0, Sort arg1) {
		return null;
	}

	public void flush() {
		
	}

	public Actorperformances getOne(Integer arg0) {
		return null;
	}

	public <S extends Actorperformances> List<S> save(Iterable<S> arg0) {
		return null;
	}

	public <S extends Actorperformances> S saveAndFlush(S arg0) {
		return null;
	}

	public Page<Actorperformances> findAll(Pageable arg0) {
		return null;
	}

	public long count() {
		return 0;
	}

	public void delete(Integer arg0) {
		
	}

	public void delete(Actorperformances arg0) {
		
	}

	public void delete(Iterable<? extends Actorperformances> arg0) {
		
	}

	public void deleteAll() {
		
	}

	public boolean exists(Integer arg0) {
		return false;
	}

	public Actorperformances findOne(Integer arg0) {
		return entity;
	}

	public <S extends Actorperformances> S save(S arg0) {
		entity = arg0;
		return arg0;
	}

	public <S extends Actorperformances> long count(Example<S> arg0) {
		return 0;
	}

	public <S extends Actorperformances> boolean exists(Example<S> arg0) {
		return false;
	}

	public <S extends Actorperformances> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		return null;
	}

	public <S extends Actorperformances> S findOne(Example<S> arg0) {
		return null;
	}
}
